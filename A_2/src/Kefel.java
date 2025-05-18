import java.io.FileWriter;
import java.io.IOException;

public class Kefel {
    public static void main(String[] args) throws IOException {
        int k = Integer.parseInt(args[0]);
        FileWriter writer = new FileWriter("kefel.s");

        writer.write(".section .text\n");
        writer.write(".globl kefel\n");
        writer.write("kefel:\n");

        if (k == 0) {
            writer.write("    movq $0, %rax\n");
            writer.write("    ret\n");
            writer.close();
            return;
        }

        boolean first = true;
        for (int shift = 63; shift >= 0; shift--) {
            if ((k & (1L << shift)) != 0) {
                if (first) {
                    writer.write("    movq %rdi, %rax\n");
                    if (shift > 0) {
                        writer.write("    shlq $" + shift + ", %rax\n");
                    }
                    first = false;
                } else {
                    writer.write("    movq %rdi, %rcx\n");
                    writer.write("    shlq $" + shift + ", %rcx\n");
                    writer.write("    addq %rcx, %rax\n");
                }
            }
        }

        writer.write("    ret\n");
        writer.close();
    }
}
