package org.omg.PortableInterceptor;


/**
* org/omg/PortableInterceptor/ClientRequestInterceptor.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from c:/workspace/9-2-build-windows-amd64-cygwin-phase2/jdk9/6180.nc/corba/src/java.corba/share/classes/org/omg/PortableInterceptor/Interceptors.idl
* Wednesday, March 8, 2017 10:24:48 PM PST
*/


/**
   * Client-side request interceptor.
   * <p>
   * A request Interceptor is designed to intercept the flow of a 
   * request/reply sequence through the ORB at specific points so that 
   * services can query the request information and manipulate the service 
   * contexts which are propagated between clients and servers. The primary 
   * use of request Interceptors is to enable ORB services to transfer 
   * context information between clients and servers. There are two types 
   * of request Interceptors: client-side and server-side. 
   * <p>
   * To write a client-side Interceptor, implement the 
   * <code>ClientRequestInterceptor</code> interface. 
   *
   * @see ClientRequestInfo
   */
public interface ClientRequestInterceptor extends ClientRequestInterceptorOperations, org.omg.PortableInterceptor.Interceptor, org.omg.CORBA.portable.IDLEntity 
{
} // interface ClientRequestInterceptor
