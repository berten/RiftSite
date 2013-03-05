package org.deschutter;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Runner {





        /**
         * Main method.
         */
        public static void main(String[] args) {


            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
            Dispatcher dispatcher = applicationContext.getBean(Dispatcher.class);
            try {
                dispatcher.dispatch(new FileReader("C:/Projects/git/RiftSite/parser/src/main/resources/CombatLog.2012.06.14.nilus.txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        }


}
