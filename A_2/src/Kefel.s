.section .text
.global kefel
kefel:
   movq %rdi , %rax
   salq $3,  %rax
   movq %rdi, %rcx
   salq $2,  %rcx
   addq %rcx, %rax
   movq %rdi, %rcx
   salq $1,  %rcx
   addq %rcx, %rax
   ret
