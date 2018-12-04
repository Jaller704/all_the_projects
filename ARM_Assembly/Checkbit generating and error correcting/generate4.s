	.global generate4

generate4:	push	{R1-R11}	@ Preserve the register values
		mov	R1,R0		@ Create a copy of R0
		mov	R11,R0		@ Create a copy of R0
		mov	R2,#1		@ The amount to shift by
		mov	R3,#1		@ Masking bit

		and	R4,R3,R1,lsr R2 @ Making a copy of the 1st bit in the sequence
		mov	R1,R1,lsr #1	@ Right shifting to the next bit in the sequence
		and	R5,R3,R1,lsr R2	@ Making a copy of the 2nd bit in the sequence
		mov	R1,R1,lsr #1	@ Right shifting  to the next bit
		and	R6,R3,R1,lsr R2 @ Making a copy of the 3rd bit in the sequence
		mov	R1,R1,lsr #1	@ Right shifting to the next bit
		and	R7,R3,R1,lsr R2 @ Making a copy of the 4th bit

		eor	R8,R7,R6	@ Exclusive OR between R7 and R6, being stored in R8
		eor	R8,R7		@ Exclusive OR between R8 and R4, generates check bit 1

		eor	R9,R7,R6	@ Exclusive OR between R7 and R6, being stored in R9
		eor	R9,R6		@ Exclusive OR between R9 and R5, generates check bit 2

		eor	R10,R7,R5	@ Exclusive OR between R7 and R5, being stored in R10
		eor	R10,R7		@ Exclusive OR between R10 and R4, generates check bit 3

		mov	R1,#0		@ Clear R1 to store the final sequence
		add	R1,R10		@ Add the 3rd check bit to R1

		mov	R1,R1, lsl #1	@ Left shift R1 by 1 to fit the next check bit

		add	R1,R9		@ Add the 2nd check bit
		mov	R1,R1, lsl #1	@ Left shift R1 by 1 to fit the next check bit

		add	R1,R8		@ Add the 1st check bit
		mov	R1,R1, lsl #4	@ Left shift R1 by 4 to fit the original 4 bit word

		add	R1,R11		@ Add the original 4 bit word

		mov	R0,R1		@ Move the completed sequence into R0

		pop	{R1-R11}	@ Restore the register values

		bx	LR		@ Return to the calling program
