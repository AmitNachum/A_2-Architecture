.section .text
.global kefel
kefel:
    movq %rdi, %rax
    shlq $3, %rax
    movq %rdi, %rcx
    shlq $2, %rcx
    addq %rcx, %rax
    movq %rdi, %rcx
    shlq $1, %rcx
    addq %rcx, %rax
    ret
.section .note.GNU-stack,"",@progbits
