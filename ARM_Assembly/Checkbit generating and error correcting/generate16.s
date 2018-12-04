	.global generate16

generate16:	push	{R1-R12}	@ Preserve registers R1 through R12
		push	{LR}		@ Preserve LR due to having a subroutine inside of this subroutine (LR gets overwritten)

emword:		mov     R1,#0		@ Clearing registers
                mov     R2,#0		@ Clearing registers
                ubfx    R1,R0,#11,#6	@ Grab the last 5 bits from R0 and put them in R1
                mov     R1,R1,lsl #1	@ Left shift R1 by 1 to fit the 5th checkbit
                add     R1,#0		@ Add the 5th checkbit (blank)
                mov     R1,R1,lsl #7	@ Left shift by 7 to fit the middle bits
                ubfx    R2,R0,#4,#7	@ Grab the 7 middle bits and put them in R2 (ubfx overwrites a registers contents)
                add     R1,R2		@ Add the contents of R2 to R1
                mov     R1,R1,lsl #1	@ Left shift by 1 to fit checkbit 4
                add     R1,#0		@ Add the 4th checkbit (blank)
                mov     R2,#0		@ Clear R2 (just in case)
                ubfx    R2,R0,#1,#3	@ Grab the 3 bits after D0
                mov     R1,R1,lsl #3	@ Left shift by 3 to fit the 3 bits
                add     R1,R2		@ Add the contents of R2 to R1
		mov     R1,R1,lsl #1	@ Left shift by 1 to fit checkbit 3
		add	R1,#0		@ Add the 3rd checkbit (blank)
		mov     R2,#0		@ Clear R2 (just in case)
		ubfx    R2,R0,#0,#1	@ Grab the 1st bit
		mov     R1,R1,lsl #1	@ Left shift by 1 to fit it
		add     R1,R2		@ Add it
		mov     R1,R1,lsl #1	@ Left shift by 1 to fit checkbit 2
		add     R1,#0		@ Add checkbit 2 (blank)
		mov     R1,R1,lsl #1	@ Left shift by 1 to fit checkbit 1
		add     R1,#0		@ Add checkbit 1 (blank)

		mov     R3,#1           @ Masking bit

genc1:		mov     R2,#2           @ The amount to jump by
		mov	R5,#0		@ Reset values
		mov     R6,#22		@ The amount of hits to count multiplied by 2
		bl	chkbit		@ Count the 1s
		tst	R5,#1		@ Test if there are an even or odd amount of 1s
		movne	R7,#1		@ Check bit is 1 if odd amount of 1s
		moveq	R7,#0		@ Check bit is 0 if even amount of 1s

genc2:		mov     R1,R1,lsr #1	@ Starting from bit 1 because bit 0 doesn't need to be counted
		mov     R2,#4           @ Jumping by 4 this time
                mov     R5,#0		@ Reset values
                mov     R6,#20		@ Counting 5 as 20/4 equals 5 and only counting half the amount of hits
		bl      chkbit
		mov     R1,R1,lsr #1    @ Starting from bit 2 as bit 1 and bit 1 + 4 etc have been counted
		mov     R6,#20		@ Reset R6, counting the other half amount of hits
		bl      chkbit
                tst     R5,#1           @ Test if there are an even or odd amount of 1s
                movne   R8,#1           @ Check bit is 1 if odd amount of 1s
                moveq   R8,#0           @ Check bit is 0 if even amount of 1s

genc3:		mov     R1,R1,lsr #1    @ Starting from bit 3 now as previous bits are not counted
                mov     R2,#8           @ jumping by 8 this time
                mov     R5,#0           @ Reset values
                mov     R6,#24		@ 24/8=3 and there are only 3 hits to count if we begin from bit 3 and jump 8 bits
                bl      chkbit
                mov     R1,R1,lsr #1    @ Starting from bit 4
                mov     R6,#24		@ Still 3 hits to count
                bl      chkbit
		mov     R1,R1,lsr #1    @ Starting from bit 5
                mov     R6,#16		@ only 2 hits to count now as the 3rd hit is past bit 20
                bl      chkbit
		mov     R1,R1,lsr #1    @ Starting from bit 6
                mov     R6,#16		@ last 2 hits
                bl      chkbit
                tst     R5,#1           @ Test if there are an even or odd amount of 1s
                movne   R9,#1          	@ Check bit is 1 if odd amount of 1s
                moveq   R9,#0          	@ Check bit is 0 if even amount of 1s

genc4:		mov     R1,R1,lsr #1    @ Starting from bit 7
		mov     R2,#1           @ No gaps so only need to jump by 1
                mov     R5,#0		@ Reset values
                mov     R6,#8		@ The amount of hits to count
                bl      chkbit          @ Count the 1s
                tst     R5,#1           @ Test if there are an even or odd amount of 1s
                movne   R10,#1          @ Check bit is 1 if odd amount of 1s
                moveq   R10,#0          @ Check bit is 0 if even amount of 1s

genc5:		mov     R1,R1,lsr #8    @ Starting from bit 15
		mov     R2,#1           @ No gaps again
		mov     R5,#0           @ Reset values
                mov     R6,#6		@ The amount of hits to count
                bl      chkbit          @ Count the 1s
		tst     R5,#1           @ Test if there are an even or odd amount of 1s
                movne   R11,#1          @ Check bit is 1 if odd amount of 1s
                moveq   R11,#0          @ Check bit is 0 if even amount of 1s

flword:		mov     R1,#0		@ Clearing registers
                mov     R2,#0		@ Clearing registers
                ubfx    R1,R0,#11,#6	@ Grab the last 5 bits from R0 and put them in R1
                mov     R1,R1,lsl #1	@ Left shift R1 by 1 to fit the 5th checkbit
                add     R1,R11		@ Add the 5th checkbit
                mov     R1,R1,lsl #7	@ Left shift by 7 to fit the middle bits
                ubfx    R2,R0,#4,#7	@ Grab the 7 middle bits and put them in R2
                add     R1,R2		@ Add the contents of R2 to R1
                mov     R1,R1,lsl #1	@ Left shift by 1 to fit checkbit 4
                add     R1,R10		@ Add the 4th checkbit
                mov     R2,#0		@ Clear R2 (just in case)
                ubfx    R2,R0,#1,#3	@ Grab the 3 bits after D0
                mov     R1,R1,lsl #3	@ Left shift by 3 to fit the 3 bits
                add     R1,R2		@ Add the contents of R2 to R1
                mov     R1,R1,lsl #1	@ Left shift by 1 to fit checkbit 3
                add     R1,R9		@ Add the 3rd checkbit
                mov     R2,#0		@ Clear R2 (just in case)
                ubfx    R2,R0,#0,#1	@ Grab the 1st bit
                mov     R1,R1,lsl #1	@ Left shift by 1 to fit it
                add     R1,R2		@ Add it
                mov     R1,R1,lsl #1	@ Left shift by 1 to fit checkbit 2
                add     R1,R8		@ Add checkbit 2
                mov     R1,R1,lsl #1	@ Left shift by 1 to fit checkbit 1
                add     R1,R7		@ Add checkbit 1

		mov	R0,R1		@ Move R1s contents (the complete 21 bit word) into R0

		pop     {LR}		@ Restore LR to it's original value
                pop     {R1-R12}	@ Restore original register contents

		bx	LR		@ Return to calling program

chkbit:		and     R4,R3,R1,lsr R6 @ Making a copy of the 1st bit then jump by R2 to the next bit in the sequence
		add	R5,R4		@ Counting the amount of 1s
		cmp	R6,#0		@ check if R6 is on its last bit
		subhi	R6,R2		@ Jumping by R2 amount
		bhi	chkbit		@ Run again until R6 = 0
		bxls	LR		@ Return to the calling subroutine if  R6 <= 0

