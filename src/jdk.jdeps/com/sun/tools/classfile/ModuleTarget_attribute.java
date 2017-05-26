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

package com.sun.tools.classfile;

import java.io.IOException;

/**
 * See JVMS, section 4.8.15.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class ModuleTarget_attribute extends Attribute {
    ModuleTarget_attribute(ClassReader cr, int name_index, int length) throws IOException {
        super(name_index, length);
        os_name_index = cr.readUnsignedShort();
        os_arch_index = cr.readUnsignedShort();
        os_version_index = cr.readUnsignedShort();
    }

    @Override
    public <R, D> R accept(Visitor<R, D> visitor, D data) {
        return visitor.visitModuleTarget(this, data);
    }

    public final int os_name_index;
    public final int os_arch_index;
    public final int os_version_index;
}
