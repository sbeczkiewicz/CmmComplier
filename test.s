		.text
		.globl main
main:			#METHOD ENTRY
		sw    $ra, 0($sp)	#PUSH
		subu  $sp, $sp, 4
		sw    $fp, 0($sp)	#PUSH
		subu  $sp, $sp, 4
		add   $fp, $sp, 8
		li    $t0, 1
		sw    $t0, 0($sp)	#PUSH
		subu  $sp, $sp, 4
		lw    $t0, 4($sp)	#POP
		addu  $sp, $sp, 4
		beq   $t0, 0, .L0
		li    $t0, 5
		sw    $t0, 0($sp)	#PUSH
		subu  $sp, $sp, 4
		la    $t0, 4($fp)
		sw    $t0, 0($sp)	#PUSH
		subu  $sp, $sp, 4
		lw    $t1, 4($sp)	#POP
		addu  $sp, $sp, 4
		lw    $t0, 4($sp)	#POP
		addu  $sp, $sp, 4
		sw    $t0, 0($t1)
			#WRITE
		lw    $t0, 4($fp)
		sw    $t0, 0($sp)	#PUSH
		subu  $sp, $sp, 4
		lw    $a0, 4($sp)	#POP
		addu  $sp, $sp, 4
		li    $v0, 1
		syscall
			#WRITE
		.data
.L1:	.asciiz "\n"
		.text
		la    $t0, .L1
		sw    $t0, 0($sp)	#PUSH
		subu  $sp, $sp, 4
		lw    $a0, 4($sp)	#POP
		addu  $sp, $sp, 4
		li    $v0, 4
		syscall
.L0:
_main_Exit:
			#FUNCTION EXIT
		lw    $ra, 0($fp)	#load return address
		move  $t0, $fp
		lw    $fp, -4($fp)	#restore FP
		move  $sp, $t0		#restore SP
		li    $v0, 10		#load exit code for syscall
		syscall
