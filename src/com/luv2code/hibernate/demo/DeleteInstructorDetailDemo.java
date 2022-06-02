package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteInstructorDetailDemo {

    public static void main(String[] args) {


        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();


        //create session

        Session session = factory.getCurrentSession();

        try {


            //start a transaction
            session.beginTransaction();

            // get the instructor detail object

            int theId = 4;

            InstructorDetail tempInstructorDetail =
                    session.get(InstructorDetail.class, theId);


            // print the instructor detail

            System.out.println("tempInstructorDetail: "
                    + tempInstructorDetail.getHobby());

            // print the associated instructor
            System.out.println("the associated instructor: " +
                    tempInstructorDetail.getInstructor().getFirstName());

            // now lets delet the instructor detail
            System.out.println("Deletin tempInstructorDetail" + tempInstructorDetail);
            session.delete(tempInstructorDetail);


            // removethe associated object reference
            // break bi-directional Link

            tempInstructorDetail.getInstructor().setInstructorDetail(null);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }catch (Exception exc){

            exc.printStackTrace();


        }

        finally {
            //handle connection leak issue
            session.close();
            factory.close();
        }

    }
}
