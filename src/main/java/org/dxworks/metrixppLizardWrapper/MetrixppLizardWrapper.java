package org.dxworks.metrixppLizardWrapper;

import org.dxworks.metrixppLizardWrapper.Lib.ConsoleArgumentsList;

public class MetrixppLizardWrapper {
    public static void main(String[] args) {
//        -DinputDir="/Users/denisfeier/Documents/node-cu-george"
//        -DoutputDir="/Users/denisfeier/Documents/metrixPP-wrapper/res"
//        -DlizardImageID="93db6ce88c38"
//        -DmetrixppImageID="50207117a4dc"
//        -Dconfig=""

        ConsoleArgumentsList instance = ConsoleArgumentsList.getInstance();

        System.out.println(instance);
    }
}
