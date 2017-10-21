package com.cooksys.ftd.assignments.socket;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.cooksys.ftd.assignments.socket.model.Student;

public class Server extends Utils {

    /**
     * Reads a {@link Student} object from the given file path
     *
     * @param studentFilePath the file path from which to read the student config file
     * @param jaxb the JAXB context to use during unmarshalling
     * @return a {@link Student} object unmarshalled from the given file path
     * @throws JAXBException 
     */
    public static Student loadStudent(String studentFilePath, JAXBContext jaxb) throws JAXBException {
        return (Student) JAXBContext.newInstance(Student.class).createUnmarshaller().unmarshal(new File("config/student.xml"));
    }

    /**
     * The server should load a {@link com.cooksys.ftd.assignments.socket.model.Config} object from the
     * <project-root>/config/config.xml path, using the "port" property of the embedded
     * {@link com.cooksys.ftd.assignments.socket.model.LocalConfig} object to create a server socket that
     * listens for connections on the configured port.
     *
     * Upon receiving a connection, the server should unmarshal a {@link Student} object from a file location
     * specified by the config's "studentFilePath" property. It should then re-marshal the object to xml over the
     * socket's output stream, sending the object to the client.
     *
     * Following this transaction, the server may shut down or listen for more connections.
     * @throws JAXBException 
     * @throws IOException 
     */
    public static void main(String[] args) throws JAXBException, IOException {
    	System.out.println("Creating server and waiting...");
    	
    	ServerSocket ssock = new ServerSocket(Utils.loadConfig("config/config.xml",Utils.createJAXBContext()).getLocal().getPort());
        Socket topSecretData = ssock.accept();
        
        System.out.println("Connection acquired! Preparing to send 1's and 0's");
        
        DataOutputStream str = new DataOutputStream(topSecretData.getOutputStream());
        
        JAXBContext studentContext = JAXBContext.newInstance(Student.class);
        
        Student awesomeStud = loadStudent("config/config.xml", studentContext);
        
        studentContext.createMarshaller().marshal(awesomeStud, str);
        
        topSecretData.close();
        ssock.close();
        
        
    }
}
