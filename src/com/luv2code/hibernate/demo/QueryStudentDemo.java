package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class QueryStudentDemo {

    public static void main(String[] args) {


        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();


        //create session

        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            // query students
            List<Student> theStudents = session.createQuery("from Student")
                    .list();

            // display the students

            displayStudents(theStudents);

            // query students : lastName="Öztürk"

            theStudents = session.createQuery("from Student s where s.lastName='Öztürk'").list();

            //display the students

            System.out.println("\n\nStudents who have last name of Öztürk");

            displayStudents(theStudents);

            // query students : lastName="Öztürk" or firstName ='Daffy'

            theStudents = session.createQuery(
                    "from Student s where s.lastName='Öztürk' " +
                            "OR s.firstName='Daffy'"
            ).list();

            System.out.println("\n\nStudents who have last name of Öztürk OR first" +
                    "name Daffy");

            displayStudents(theStudents);


            // query students where email LIKE '%luv2code.com'

            theStudents = session.createQuery("from Student s where " +
                    "s.email LIKE '%luv2code.com'").list();

            System.out.println("\n\nStudents whose have email ends with luv2.code.com");

            displayStudents(theStudents);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }

    }

    private static void displayStudents(List<Student> theStudents) {
        for (Student tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }
}
