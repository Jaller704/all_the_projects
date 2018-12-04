	.global correct4

correct4:	push	{R1-R11}	@ Preserve the register values
		mov	R1,R0		@ Create a copy of R0
		mov	R11,R0		@ Create a copy of R0
		mov	R2,#1		@ The amount to shift by
		mov	R3,#1		@ Masking bit

		mov	R1,R1,lsl #1	@ Left shift by one or the first bit to be copied will actually be the second in the sequence
		and	R4,R3,R1,lsr R2 @ Making a copy of the 1st bit (RX0) in the sequence (from the right)
		mov	R1,R1,lsr #1	@ Right shifting to the next bit in the sequence
		and	R5,R3,R1,lsr R2	@ Making a copy of the 2nd bit (RX1) in the sequence
		mov	R1,R1,lsr #1	@ Right shifting  to the next bit
		and	R6,R3,R1,lsr R2 @ Making a copy of the 3rd bit (RX2) in the sequence
		mov	R1,R1,lsr #1	@ Right shifting to the next bit
		and	R7,R3,R1,lsr R2 @ Making a copy of the 4th bit (RX3) in the sequence
		mov	R1,R1,lsr #1	@ Right shifting to the next bit
		and	R8,R3,R1,lsr R2 @ Making a copy of the 5th bit (chkbit 1,RX4) in the sequence
		mov	R1,R1,lsr #1	@ Right shifting to the next bit
		and	R9,R3,R1,lsr R2 @ Making a copy of the 6th bit (chkbit 2,RX5) in the sequence
		mov	R1,R1,lsr #1	@ Right shifting to the next bit
		and	R10,R3,R1,lsr R2 @ Making a copy of the 7th bit (chkbit 6,RX6) in the sequence

@		Copying out the block of eor gates: R1, R2, and R3 will contain the generated bits to be ANDed together

		eor	R1,R8,R7	@ Exclusive OR between RX4 and RX3, being stored in R1
		eor	R1,R6		@ Exclusive OR between RX2 and R1
		eor	R1,R4		@ Exclusive OR between RX0 and R1

		eor	R2,R9,R7	@ Exclusive OR between RX5 and RX3, being stored in R2
		eor	R2,R6		@ Exclusive OR between RX2 and R2
		eor     R2,R5           @ Exclusive OR between RX1 and R2

		eor	R3,R10,R7	@ Exclusive OR between RX6 and RX3, being stored in R3
		eor	R3,R5		@ Exclusive OR between R3 and RX1
		eor	R3,R4		@ Exclusive OR between R3 and RX0

@		ANDing them all together, RX4,5, and 6 are not needed anymore so their registers can be reused for storage

		and	R0,R1,R2	@ AND the 3 generated bits, store in R0
		and	R0,R3
		eor	R2,#1		@ Equivalent to NOT
		and	R8,R1,R2	@ AND the 3 bits, store in R4
		and	R8,R3
		eor	R2,#1		@ reversing the previous NOT
		eor	R1,#1
		and	R9,R1,R2	@ AND the 3 bits, store in R5
		and	R9,R3
		eor	R1,#1		@ Reversing the previous NOT
		eor	R3,#1
		and	R10,R1,R2	@ AND the 3 bits, store in R6
		and	R10,R3

@		EORing the results to generate the correct 4 bits

		eor	R4,R8		@ Generate D0
		eor	R5,R9		@ Generate D1
		eor	R6,R10		@ Generate D2
		eor	R7,R0		@ Generate D3

		mov	R1,#0		@ Clear R1 to store the final sequence
		add	R1,R7		@ Add D3 to R1

		mov	R1,R1, lsl #1	@ Left shift R1 by 1 to fit the next bit

		add	R1,R6		@ Add D2
		mov	R1,R1, lsl #1	@ Left shift R1 by 1 to fit the next bit

		add	R1,R5		@ Add D1
		mov	R1,R1, lsl #1	@ Left shift R1 by 1 to fit the last bit

		add	R1,R4		@ Add D0

		mov	R0,R1		@ Move the completed sequence into R0

		pop	{R1-R11}	@ Restore the register values

		bx	LR		@ Return to the calling program
