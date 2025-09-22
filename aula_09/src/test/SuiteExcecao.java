package test;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory({Excecao.class})
@Suite.SuiteClasses({
        PessoaTest.class,
        DescendenciaTest.class,
        MatrimonioTest.class,
        RemoverTest.class,
        IrmaosTest.class,
        TiosTest.class
})

public class SuiteExcecao {
}
