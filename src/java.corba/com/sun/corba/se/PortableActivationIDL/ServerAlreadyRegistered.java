package com.sun.corba.se.PortableActivationIDL;


/**
* com/sun/corba/se/PortableActivationIDL/ServerAlreadyRegistered.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from c:/workspace/9-2-build-windows-amd64-cygwin-phase2/jdk9/6180.nc/corba/src/java.corba/share/classes/com/sun/corba/se/PortableActivationIDL/activation.idl
* Wednesday, March 8, 2017 10:24:43 PM PST
*/

public final class ServerAlreadyRegistered extends org.omg.CORBA.UserException
{
  public String serverId = null;

  public ServerAlreadyRegistered ()
  {
    super(ServerAlreadyRegisteredHelper.id());
  } // ctor

  public ServerAlreadyRegistered (String _serverId)
  {
    super(ServerAlreadyRegisteredHelper.id());
    serverId = _serverId;
  } // ctor


  public ServerAlreadyRegistered (String $reason, String _serverId)
  {
    super(ServerAlreadyRegisteredHelper.id() + "  " + $reason);
    serverId = _serverId;
  } // ctor

} // class ServerAlreadyRegistered