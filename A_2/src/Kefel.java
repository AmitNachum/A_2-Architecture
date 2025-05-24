import java.io.FileWriter;

public class Kefel {
    public static void main(String[] args) throws Exception {
        int k = Integer.parseInt(args[0]);
        FileWriter w = new FileWriter("kefel.s");

        // Proper section and global directive on separate lines
        w.write(".section .text\n.global kefel\nkefel:\n");

        if (k == 0) {
            w.write("    movq $0, %rax\n    ret\n");
            w.write(".section .note.GNU-stack,\"\",@progbits\n");
            w.close();
            return;
        }

        boolean first = true;
        for (int i = 63; i >= 0; i--) {
            if ((k & (1L << i)) != 0) {
                if (first) {
                    w.write("    movq %rdi, %rax\n");
                    if (i > 0) w.write("    shlq $" + i + ", %rax\n");
                    first = false;
                } else {
                    w.write("    movq %rdi, %rcx\n");
                    if (i > 0) w.write("    shlq $" + i + ", %rcx\n");
                    w.write("    addq %rcx, %rax\n");
                }
            }
        }

        w.write("    ret\n");
        w.write(".section .note.GNU-stack,\"\",@progbits\n");
        w.close();
    }
}
