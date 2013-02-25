package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.control.ManterResSalaProfessorTest;
import test.model.ReservaSalaProfessorTest;
import test.persistence.ResSalaProfessorDAOTest;

@RunWith(Suite.class)
@SuiteClasses({ReservaSalaProfessorTest.class, ResSalaProfessorDAOTest.class, ManterResSalaProfessorTest.class})
public class ReservaSalaProfessorSuite {

}
