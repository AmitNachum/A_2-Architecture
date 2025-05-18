import java.io.FileWriter;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Kefel {
    public static void main(String[] args) throws IOException {
        int k = Integer.parseInt(args[0]);

        FileWriter writer = new FileWriter("Kefel.s");

        writer.write(".section .text\n");
        writer.write(".global kefel\n");
        writer.write("kefel:\n");


        if (k == 0) {
            writer.write("   movq $0 , %rax\n");
            writer.write("   ret\n");
            writer.close();
            return;
        }

        boolean first = true;


        for (int shift = 63; shift >= 0; shift--) {
            long mask = 1;
            if ((k & (mask << shift)) != 0) {
                if (first) {
                    writer.write("   movq %rdi , %rax\n");

                    if (shift > 0)
                        writer.write("   salq $" + shift + ",  %rax\n");
                    first = false;

                } else {
                    writer.write("   movq %rdi, %rcx\n");
                    writer.write("   salq $" + shift + ",  %rcx\n");
                    writer.write("   addq %rcx, %rax\n");
                }
            }
        }

        writer.write("   ret\n");
        writer.close();
    }
}