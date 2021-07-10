package fr.lernejo;

//import static org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SampleTest {
    Sample smp = new Sample ();

    @Test
    void test_fact() {
        int n = 6;
        int res = smp.fact(n);
        org.assertj.core.api.Assertions.assertThat(res).as("fact of 6")
            .isEqualTo(720);
    }

    @Test
    void wrong_arg() {
        int n = -1;
        org.assertj.core.api.Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> smp.fact(n));
    }

    @Test
    void test_op() {
        int a = 2;
        int b = 5;
        int add = smp.op(Sample.Operation.ADD,a,b);
        int mu = smp.op(Sample.Operation.MULT,a,b);
        org.assertj.core.api.Assertions.assertThat(add).as("add 2 and 5")
            .isEqualTo(7);
        org.assertj.core.api.Assertions.assertThat(mu).as("multiply 2 and 5")
            .isEqualTo(10);

    }
}
