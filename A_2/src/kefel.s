.section .text
.globl kefel
kefel:
    movq %rdi, %rax
    shlq $2, %rax
    ret
