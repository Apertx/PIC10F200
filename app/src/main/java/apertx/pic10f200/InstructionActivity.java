package apertx.pic10f200;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.widget.AdapterView.*;
import android.view.*;

public class InstructionActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] dict = {"ADDWF","ANDWF","CLRF","CLRW","COMF","DECF","DECFSZ","INCF","INCFSZ","IORWF","MOVF","MOVWF","NOP","RLF","RRF","SUBWF","SWAPF","XORWF","__BIT ORIENTED FILE REGISTER OPERATIONS__","BCF","BSF","BTFSC","BTFSS","__LITERAL AND CONTROL OPERATIONS__","ANDLW","CALL","CLRWDT","GOTO","IORLW","MOVLW","OPTION","RETLW","SLEEP","TRIS","XORLW"};
		final String[] info = {
			"[label] ADDWF f,d_Operands: 0 ≤ f ≤ 31; 0 ≤ d ≤ 1\nOperation: (W) + (f) -> (dest)\nStatus Affected: C, DC, Z\n\nDesc: Add the contents of the W register and register 'f'. If 'd' is '0', the result is stored in the W register. If 'd' is '1', the result is stored back in register 'f'.\n\nOpcode: 0001 11df ffff",
			"[label] ANDWF f,d_Operands: 0 ≤ f ≤ 31; 0 ≤ d ≤ 1\nOperation: (W) .AND. (f) -> (dest)\nStatus Affected: Z\n\nDesc: The contents of the W register are AND'ed with register 'f'. If 'd' is '0', the result is stored in the W register. If 'd' is '1', the result is stored back in register 'f'.\n\nOpcode: 0001 10df ffff",
			"[label] CLRF f_Operands: 0 ≤ f ≤ 31\nOperation: 00h -> f; 1 -> Z\nStatus Affected: Z\n\nDesc: The contents of register 'f' are cleared and the Z bit is set.\n\nOpcode: 0000 011f ffff",
			"[label] CLRW_Operands: None\nOperation: 00h -> W; 1 -> Z\nStatus Affected: Z\n\nDesc: The W register is cleared. Zero bit (Z) is set.\n\nOpcode: 0000 0100 0000",
			"[label] COMF f,d_Operands: 0 ≤ f ≤ 31; 0 ≤ d ≤ 1\nOperation: not(f) -> (dest)\nStatus Affected: Z\n\nDesc: The contents of register 'f' are complemented. If 'd' is '0', the result is stored in the W register. If 'd' is '1', the result is stored back in register 'f'.\n\nOpcode: 0010 01df ffff",
			"[label] DECF f,d_Operands: 0 ≤ f ≤ 31; 0 ≤ d ≤ 1\nOperation: (f) - 1 -> (dest)\nStatus Affected: Z\n\nDesc: Decrement register 'f'. If 'd' is '0', the result is stored in the W register. If 'd' is '1', the result is stored back in register 'f'.\n\nOpcode: 0000 11df ffff",
			"[label] DECFSZ f,d_Operands: 0 ≤ f ≤ 31; 0 ≤ d ≤ 1\nOperation: (f) - 1 -> (dest); skip if result = 0\nStatus Affected: None\n\nDesc: The contents of register 'f' are decremented. If 'd' is '0', the result is placed in the W register. If 'd' is '1', the result is placed back in register 'f'.\nIf the result is '0', the next instruction, which is already fetched, is discarded and a NOP is executed instead making it a 2-cycle instruction.\n\nOpcode: 0010 11df ffff",
			"[label] INCF f,d_Operands: 0 ≤ f ≤ 31; 0 ≤ d ≤ 1\nOperation: (f) + 1 -> (dest)\nStatus Affected: Z\n\nDesc: The contents of register 'f' are incremented. If 'd' is '0', the result is placed in the W register. If 'd' is '1', the result is placed back in register 'f'.\n\nOpcode: 0010 11df ffff",
			"[label] INCFSZ f,d_Operands: 0 ≤ f ≤ 31; 0 ≤ d ≤ 1\nOperation: (f) + 1 -> (dest); skip if result = 0\nStatus Affected: None\n\nDesc: The contents of register 'f' are incremented. If 'd' is '0', the result is placed in the W register. If 'd' is '1', the result is placed back in register 'f'.\nIf the result is '0', then the next instruction, which is already fetched, is discarded and a NOP is executed instead making it a 2-cycle instruction.\n\nOpcode: 0011 11df ffff",
			"[label] IORWF f,d_Operands: 0 ≤ f ≤ 31; 0 ≤ d ≤ 1\nOperation: (W) .OR. (f) -> (dest)\nStatus Affected: Z\n\nDesc: Inclusive OR the W register with register 'f'. If 'd' is '0', the result is placed in the W register. If 'd' is '1', the result is placed back in register 'f'.\n\nOpcode: 0001 00df ffff",
			"[label] MOVF f,d_Operands: 0 ≤ f ≤ 31; 0 ≤ d ≤ 1\nOperation: (f) -> (dest)\nStatus Affected: Z\n\nDesc: The contents of register 'f' are moved to destination 'd'. If 'd' is '0', destination is the W register. If 'd' is '1', the destination is file register 'f'. 'd' = 1 is useful as a test of a file register, since status flag Z is affected.\n\nOpcode: 0010 00df ffff",
			"[label] MOVWF f_Operands: 0 ≤ f ≤ 31\nOperation: (W) -> (f)\nStatus Affected: None\n\nDesc: Move data from the W register to register 'f'.\n\nOpcode: 0000 001f ffff",
			"[label] NOP f,d_Operands: None\nOperation: No operation\nStatus Affected: None\n\nDesc: No operation\n\nOpcode: 0000 0000 0000",
			"[label] RLF f,d_Operands: 0 ≤ f ≤ 31; 0 ≤ d ≤ 1\nOperation: See description below\nStatus Affected: C\n\nDesc: The contents of register 'f' are rotated one bit to the left through the Carry flag. If 'd' is '0', the result is placed in the W register. If 'd' is '1', the result is stored back in register 'f'.\n\nOpcode: 0011 01df ffff",
			"[label] RRF f,d_Operands: 0 ≤ f ≤ 31; 0 ≤ d ≤ 1\nOperation: See description below\nStatus Affected: C\n\nDesc: The contents of register 'f' are rotated one bit to the right through the Carry flag. If 'd' is '0', the result is placed in the W register. If 'd' is '1', the result is placed back in register 'f'.\n\nOpcode: 0011 00df ffff",
			"[label] SUBWF f,d_Operands: 0 ≤ f ≤ 31; 0 ≤ d ≤ 1\nOperation: (W) - (f) -> (dest)\nStatus Affected: C, DC, Z\n\nDesc: Subtract (2's complement method) the W register from register 'f'. If 'd' is '0', the result is stored in the W register. If 'd' is '1', the result is stored back in register 'f'.\n\nOpcode: 0000 10df ffff",
			"[label] XXX f,d_Operands: 0 ≤ f ≤ 31, 0 ≤ d ≤ 1\nOperation: (f<3:0>) -> (dest<7:4>); (f<7:4>) -> (dest<3:0>)\nStatus Affected: None\n\nDesc: The upper and lower nibbles of register 'f' are exchanged. If 'd' is '0', the result is placed in W register. If 'd' is '1', the result is placed in register 'f'.\n\nOpcode: 0011 10df ffff",
			"[label] XORWF f,d_Operands: 0 ≤ f ≤ 31; 0 ≤ d ≤ 1\nOperation: (W) .XOR. (f) -> (dest)\nStatus Affected: Z\n\nDesc: Exclusive OR the contents of the W register with register 'f'. If 'd' is '0', the result is stored in the W register. If 'd' is '1', the result is stored back in register 'f'.\n\nOpcode: 0001 10df ffff",
			"",
			"[label] BCF f,d_Operands: 0 ≤ f ≤ 31; 0 ≤ b ≤ 7\nOperation: 0 -> (f<b>)\nStatus Affected: None\n\nDesc: Bit 'b' in register 'f' is cleared.\n\nOpcode: 0100 bbbf ffff",
			"[label] BSF f,d_Operands: 0 ≤ f ≤ 31; 0 ≤ b ≤ 7\nOperation: 1 -> (f<b>)\nStatus Affected: None\n\nDesc: Bit 'b' in register 'f' is set.\n\nOpcode: 0101 bbbf ffff",
			"[label] BTFSC f,d_Operands: 0 ≤ f ≤ 31; 0 ≤ b ≤ 7\nOperation: skip if (f<b>) = 0\nStatus Affected: None\n\nDesc: If bit 'b' in register 'f' is '0', then the next instruction is skipped.\nIf bit 'b' is '0', then the next instruction fetched during the current instruction execution is discarded, and a NOP is executed instead, making this a 2-cycle instruction.\n\nOpcode: 0110 bbbf ffff",
			"[label] BTFSS f,d_Operands: 0 ≤ f ≤ 31; 0 ≤ b ≤ 7\nOperation: skip if (f<b>) = 1\nStatus Affected: None\n\nDesc: If bit 'b' in register 'f' is '1', then the next instruction is skipped.\nIf bit 'b' is '1', then the next instruc-tion fetched during the current instruction execution, is discarded and a NOP is executed instead, making this a 2-cycle instruction.\n\nOpcode: 0111 bbbf ffff",
			"",
			"[label] ANDLW k_Operands: 0 ≤ k ≤ 255\nOperation: (W) .AND. (k) -> (W)\nStatus Affected: Z\n\nDesc: The contents of the W register are AND'ed with the 8-bit literal 'k'. The result is placed in the W register.\n\nOpcode: 1110 kkkk kkkk",
			"[label] CALL k_Operands: 0 ≤ k ≤ 255\nOperation: (PC) + 1 -> top-of-stack;\nk -> PC<7:0>;\n(STATUS<6:5>) -> PC<10:9>;\n0 -> PC<8>\nStatus Affected: None\n\nDesc: Subroutine call. First, return address (PC + 1) is PUSHed onto the stack. The 8-bit immediate address is loaded into PC bits <7:0>. The upper bits PC<10:9> are loaded from STATUS<6:5>, PC<8> is cleared. CALL is a 2-cycle instruction.\n\nOpcode: 1001 kkkk kkkk",
			"[label] CLRWDT_Operands: None\nOperation: 00h -> WDT;\n0 -> WDT prescaler (if assigned);\n1 -> {TO};\n1 -> {PD}\nStatus Affected: {TO}, {PD}\n\nDesc: The CLRWDT instruction resets the WDT. It also resets the prescaler, if the prescaler is assigned to the WDT and not Timer0. Status bits TO and PD are set.\n\nOpcode: 0000 0000 0100",
			"[label] GOTO k_Operands: 0 ≤ k ≤ 511\nOperation: k -> PC<7:0>;\nSTATUS<6:5> -> PC<10:9>\nStatus Affected: None\n\nDesc: GOTO is an unconditional branch. The 9-bit immediate value is loaded into PC bits <8:0>. The upper bits of PC are loaded from STATUS<6:5>. GOTO is a 2-cycle instruction.\n\nOpcode: 101k kkkk kkkk",
			"[label] IORLW k_Operands: 0 ≤ k ≤ 255\nOperation: (W) .OR. (k) -> (W)\nStatus Affected: Z\n\nDesc: The contents of the W register are OR'ed with the 8-bit literal 'k'. The result is placed in the W register.\n\nOpcode: 1101 kkkk kkkk",
			"[label] MOVLW k_Operands: 0 ≤ k ≤ 255\nOperation: k -> (W)\nStatus Affected: None\n\nDesc: The 8-bit literal 'k' is loaded into the W register. The \"don't cares\" will assembled as '0's.\n\nOpcode: 1100 kkkk kkkk",
			"[label] OPTION_Operands: None\nOperation: (W) -> Option\nStatus Affected: None\n\nDesc: The content of the W register is loaded into the OPTION register.\n\nOpcode: 0000 0000 0010",
			"[label] RETLW k_Operands: 0 ≤ k ≤ 255\nOperation: k -> (W); TOS -> PC\nStatus Affected: None\n\nDesc: The W register is loaded with the 8-bit literal 'k'. The program counter is loaded from the top of the stack (the return address). This is a 2-cycle instruction.\n\nOpcode: 1000 kkkk kkkk",
			"[label] SLEEP_Operands: None\nOperation: 00h -> WDT;\n1 -> {TO};\n0 -> {PD}\nStatus Affected: {TO}, {PD}, PBWUF\n\nDesc: Time-out Status bit (TO) is set. The Power-down Status bit (PD) is cleared. RBWUF is unaffected.\nThe WDT and its prescaler are cleared.\nThe processor is put into Sleep mode with the oscillator stopped.\n\nOpcode: 0000 0000 0011",
			"[label] TRIS k_Operands: f = 6\nOperation: (W) -> TRIS register f\nStatus Affected: None\n\nDesc: TRIS register 'f' (f = 6 or 7) is loaded with the contents of the W register.\n\nOpcode: 0000 0000 0fff",
			"[label] XORLW_Operands: 0 ≤ k ≤ 255\nOperation: (W) .XOR. k -> (W)\nStatus Affected: Z\n\nDesc: The contents of the W register are XOR'ed with the 8-bit literal 'k'. The result is placed in the W register.\n\nOpcode: 1111 kkkk kkkk"
		};
		ListView list = new ListView(this);
		list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dict));
		list.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4) {
					if (p3 != 18 && p3 != 23)
						new AlertDialog.Builder(InstructionActivity.this).
							setTitle(info[p3].split("_")[0]).
							setMessage(info[p3].split("_")[1]).
							setPositiveButton("OK", null).show();
				}
			});
		setContentView(list);
	}
}
