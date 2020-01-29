package com.rest.quiz.restQuiz;

import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DozerBeanMapper mapper=new DozerBeanMapper();
        List myMappingFiles = new ArrayList();
        myMappingFiles.add("./DozerMappings_customconverters.xml");
        mapper.setMappingFiles(myMappingFiles);

        AddressType addrType=new AddressType();
        addrType.setAddrLine1("121 Howard Lane");
        addrType.setCity("Orlando");
        addrType.setState("Florida");
        addrType.setZipCode(32050);
        Address destAddr=mapper.map(addrType, Address.class);
        // Convert Address Type to Address Domain
        System.out.println("AddressType=>Address:"+destAddr.toString());

        //Convert Address domain to AddressType
        AddressType destAddrType=mapper.map(destAddr, AddressType.class);
        System.out.println("Address=>AddressType:"+destAddrType.toString());

    }
}
