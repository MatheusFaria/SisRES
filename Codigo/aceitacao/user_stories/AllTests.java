package user_stories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;



@RunWith(Suite.class)
@SuiteClasses({US01_ReservarSala.class, US02_ReservarEquipamento.class,
    US03_CadastrarAluno.class, US04_AlterarAluno.class, US05_ExcluirAluno.class, 
    US03_CadastrarProfessor.class, US04_AlterarProfessor.class, US05_ExcluirProfessor.class, 
    US06_CadastrarSala.class, US07_AlterarSala.class, US08_ExcluirSala.class,
    US09_CadastrarEquipamento.class, US10_AlterarEquipamento.class, US11_ExcluirEquipamento.class})

public class AllTests {

}
