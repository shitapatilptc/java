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

/**
 * Implementation of the edit pad service.
 *
 * @since 9
 */
module jdk.editpad {
    requires jdk.internal.ed;
    requires java.desktop;
    provides jdk.internal.editor.spi.BuildInEditorProvider
              with jdk.editpad.EditPadProvider;
}
