package com.sun.corba.se.spi.activation;


/**
* com/sun/corba/se/spi/activation/ORBPortInfo.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from c:/workspace/9-2-build-windows-amd64-cygwin-phase2/jdk9/6180.nc/corba/src/java.corba/share/classes/com/sun/corba/se/spi/activation/activation.idl
* Wednesday, March 8, 2017 10:24:44 PM PST
*/

public final class ORBPortInfo implements org.omg.CORBA.portable.IDLEntity
{
  public String orbId = null;
  public int port = (int)0;

  public ORBPortInfo ()
  {
  } // ctor

  public ORBPortInfo (String _orbId, int _port)
  {
    orbId = _orbId;
    port = _port;
  } // ctor

} // class ORBPortInfo
