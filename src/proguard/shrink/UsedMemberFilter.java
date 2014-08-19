/*
 * ProGuard -- shrinking, optimization, obfuscation, and preverification
 *             of Java bytecode.
 *
 * Copyright (c) 2002-2014 Eric Lafortune (eric@graphics.cornell.edu)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package proguard.shrink;

import proguard.classfile.*;
import proguard.classfile.visitor.MemberVisitor;

/**
 * This MemberVisitor delegates all its method calls to another MemberVisitor,
 * but only for Member objects that are marked as used.
 *
 * @see UsageMarker
 *
 * @author Eric Lafortune
 */
public class UsedMemberFilter
implements   MemberVisitor
{
    private final UsageMarker   usageMarker;
    private final MemberVisitor memberVisitor;


    /**
     * Creates a new UsedMemberFilter.
     * @param usageMarker   the usage marker that is used to mark the classes
     *                      and class members.
     * @param memberVisitor the member visitor to which the visiting will be
     *                      delegated.
     */
    public UsedMemberFilter(UsageMarker   usageMarker,
                            MemberVisitor memberVisitor)
    {
        this.usageMarker   = usageMarker;
        this.memberVisitor = memberVisitor;
    }


    // Implementations for MemberVisitor.


    public void visitProgramField(ProgramClass programClass, ProgramField programField)
    {
        if (usageMarker.isUsed(programField))
        {
            memberVisitor.visitProgramField(programClass, programField);
        }
    }


    public void visitProgramMethod(ProgramClass programClass, ProgramMethod programMethod)
    {
        if (usageMarker.isUsed(programMethod))
        {
            memberVisitor.visitProgramMethod(programClass, programMethod);
        }
    }


    public void visitLibraryField(LibraryClass libraryClass, LibraryField libraryField)
    {
        if (usageMarker.isUsed(libraryField))
        {
            memberVisitor.visitLibraryField(libraryClass, libraryField);
        }
    }


    public void visitLibraryMethod(LibraryClass libraryClass, LibraryMethod libraryMethod)
    {
        if (usageMarker.isUsed(libraryMethod))
        {
            memberVisitor.visitLibraryMethod(libraryClass, libraryMethod);
        }
    }
}
