package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({EquipamentoSuite.class, ProfessorSuite.class, AlunoSuite.class, SalaSuite.class,
				ReservaSalaAlunoSuite.class, ReservaSalaProfessorSuite.class})
public class AllTests {

}
