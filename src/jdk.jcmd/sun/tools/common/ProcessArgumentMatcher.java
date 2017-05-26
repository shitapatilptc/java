/*
 * Copyright (c) 2016, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package sun.tools.common;

import java.lang.reflect.Module;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.MonitoredHost;
import sun.jvmstat.monitor.MonitoredVm;
import sun.jvmstat.monitor.MonitoredVmUtil;
import sun.jvmstat.monitor.VmIdentifier;

/**
 * Class for finding process matching a process argument,
 * excluding tool it self and returning a list containing
 * the process identifiers.
 */
public class ProcessArgumentMatcher {
    private String matchClass;
    private String singlePid;

    public ProcessArgumentMatcher(String pidArg) {
        if (pidArg == null || pidArg.isEmpty()) {
            throw new IllegalArgumentException("Pid string is invalid");
        }
        if (pidArg.charAt(0) == '-') {
            throw new IllegalArgumentException("Unrecognized " + pidArg);
        }
        try {
            long pid = Long.parseLong(pidArg);
            if (pid != 0) {
                singlePid = String.valueOf(pid);
            }
        } catch (NumberFormatException nfe) {
            matchClass = pidArg;
        }
    }

    private static String getExcludeStringFrom(Class<?> excludeClass) {
        if (excludeClass == null) {
            return "";
        }
        Module m = excludeClass.getModule();
        if (m.isNamed()) {
            return m.getName() + "/" + excludeClass.getName();
        }
        return excludeClass.getName();
    }

    private static boolean check(VirtualMachineDescriptor vmd, String excludeClass, String partialMatch) {
        String mainClass = null;
        try {
            VmIdentifier vmId = new VmIdentifier(vmd.id());
            MonitoredHost monitoredHost = MonitoredHost.getMonitoredHost(vmId);
            MonitoredVm monitoredVm = monitoredHost.getMonitoredVm(vmId, -1);
            mainClass = MonitoredVmUtil.mainClass(monitoredVm, true);
            monitoredHost.detach(monitoredVm);
        } catch (NullPointerException npe) {
            // There is a potential race, where a running java app is being
            // queried, unfortunately the java app has shutdown after this
            // method is started but before getMonitoredVM is called.
            // If this is the case, then the /tmp/hsperfdata_xxx/pid file
            // will have disappeared and we will get a NullPointerException.
            // Handle this gracefully....
            return false;
        } catch (MonitorException | URISyntaxException e) {
            return false;
        }

        if (excludeClass != null && mainClass.equals(excludeClass)) {
            return false;
        }

        if (partialMatch != null && mainClass.indexOf(partialMatch) == -1) {
            return false;
        }

        return true;
    }

    private static Collection<VirtualMachineDescriptor> getSingleVMD(String pid) {
        Collection<VirtualMachineDescriptor> vids = new ArrayList<>();
        List<VirtualMachineDescriptor> vmds = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : vmds) {
            if (check(vmd, null, null)) {
                if (pid.equals(vmd.id())) {
                    vids.add(vmd);
                }
            }
        }
        return vids;
    }

    private static Collection<VirtualMachineDescriptor> getVMDs(Class<?> excludeClass, String partialMatch) {
        String excludeCls = getExcludeStringFrom(excludeClass);
        Collection<VirtualMachineDescriptor> vids = new ArrayList<>();
        List<VirtualMachineDescriptor> vmds = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : vmds) {
            if (check(vmd, excludeCls, partialMatch)) {
                vids.add(vmd);
            }
        }
        return vids;
    }

    public Collection<VirtualMachineDescriptor> getVirtualMachineDescriptors(Class<?> excludeClass) {
        if (singlePid != null) {
            return getSingleVMD(singlePid);
        } else {
            return getVMDs(excludeClass, matchClass);
        }
    }

    public Collection<VirtualMachineDescriptor> getVirtualMachineDescriptors() {
        return this.getVirtualMachineDescriptors(null);
    }

}
