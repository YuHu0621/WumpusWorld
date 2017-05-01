
import aima.core.logic.propositional.inference.PLFCEntails;
import aima.core.logic.propositional.kb.KnowledgeBase;
import aima.core.logic.propositional.parsing.ast.PropositionSymbol;

public class wumpusKnowledgeBase {
	private static PLFCEntails plfce = new PLFCEntails();
	private static KnowledgeBase kb;
	private int size;

	/**
	 * initialize the knowledge base
	 */

	public wumpusKnowledgeBase(int size) {
		this.size = size;
		kb = new KnowledgeBase();
		initializeKB();
	}

	// tentative alternative
	private void initializeKB() {
		// A: ~B, ~S
		// B
		// S
		// SB
		// NP: ~P
		// NW: ~W
		// WP: W, P
		// SAFE CASES
		kb.tell("A00 => NW01 & NP01 & NW10 & NP10");
		kb.tell("A02 => NW01 & NP01 & NW12 & NP12");
		kb.tell("A20 => NW10 & NP10 & NW21 & NP21");
		kb.tell("A22 => NW21 & NP21 & NW12 & NP12");
		kb.tell("A11 => NW10 & NP10 & NW01 & NP01 & NW21 & NP21 & NW12 & NP12");
		kb.tell("A10 => NW00 & NP00 & NW11 & NP11 & NW20 & NP20");
		kb.tell("A01 => NW00 & NP00 & NW11 & NP11 & NW02 & NP02");
		kb.tell("A12 => NW02 & NP02 & NW11 & NP11 & NW22 & NP22");
		kb.tell("A21 => NW22 & NP22 & NW11 & NP11 & NW20 & NP20");

		// EXCLUSIVE SMELL OR BREEZE
		kb.tell("B00 => NW01 & NW10");
		kb.tell("B02 => NW01 & NW12");
		kb.tell("B20 => NW10 & NW21");
		kb.tell("B22 => NW21 & NW12");
		kb.tell("B11 => NW10 & NW01 & NW21 & NW12");
		kb.tell("B10 => NW00 & NW11 & NW20");
		kb.tell("B01 => NW00 & NW11 & NW02");
		kb.tell("B12 => NW02 & NW11 & NW22");
		kb.tell("B21 => NW22 & NW11 & NW20");

		// EXCLUSIVE SMELL OR BREEZE
		kb.tell("S00 => NP01 & NP10");
		kb.tell("B02 => NP01 & NP12");
		kb.tell("S20 => NP10 & NP21");
		kb.tell("S22 => NW21 & NP12");
		kb.tell("S11 => NP10 & NP01 & NP21 & NP12");
		kb.tell("S10 => NP00 & NP11 & NP20");
		kb.tell("S01 => NP00 & NP11 & NP02");
		kb.tell("S12 => NP02 & NP11 & NP22");
		kb.tell("S21 => NP22 & NP11 & NP20");

		// FIND PIT LOGIC
		kb.tell("B00 & NP01 => P10");
		kb.tell("B00 & NP10 => P01");
		kb.tell("B02 & NP01 => P12");
		kb.tell("BO2 & NP12 => P01");
		kb.tell("B22 & NP12 => P21");
		kb.tell("B22 & NP21 => P12");
		kb.tell("B20 & NP10 => P21");
		kb.tell("B2O & NP21 => P10");
		kb.tell("B11 & NP12 & NP21 & NP10 => P01");
		kb.tell("B11 & NP01 & NP12 & NP21 => P10");
		kb.tell("B11 & NP01 & NP10 & NP12 => P21");
		kb.tell("B11 & NP01 & NP10 & NP21 => P12");
		kb.tell("B10 & NP00 & NP11 => P20");
		kb.tell("B10 & NP00 & NP20 => P11");
		kb.tell("B10 & NP11 & NP20 => P00");
		kb.tell("B01 & NP00 & NP11 => P02");
		kb.tell("B01 & NP00 & NP02 => P11");
		kb.tell("B01 & NP11 & NP02 => P00");
		kb.tell("B12 & NP02 & NP11 => P22");
		kb.tell("B12 & NP02 & NP22 => P11");
		kb.tell("B12 & NP22 & NP11 => P02");
		kb.tell("B21 & NP22 & NP11 => P20");
		kb.tell("B21 & NP22 & NP20 => P11");
		kb.tell("B21 & NP11 & NP20 => P22");

		// FIND WUMPUS LOGIC
		kb.tell("S00 & NW01 => W10");
		kb.tell("SOO & NW10 => W01");
		kb.tell("S02 & NW01 => W12");
		kb.tell("SO2 & NW12 => W01");
		kb.tell("S22 & NW12 => W21");
		kb.tell("S22 & NW21 => W12");
		kb.tell("S20 & NW10 => W21");
		kb.tell("S2O & NW21 => W10");
		kb.tell("S11 & NW12 & NW21 & NW10 => W01");
		kb.tell("S11 & NW01 & NW12 & NW21 => W10");
		kb.tell("S11 & NW01 & NW10 & NW12 => W21");
		kb.tell("S11 & NW01 & NW10 & NW21 => W12");
		kb.tell("S10 & NW00 & NW11 => W20");
		kb.tell("S10 & NW00 & NW20 => W11");
		kb.tell("S10 & NW11 & NW20 => W00");
		kb.tell("S01 & NW00 & NW11 => W02");
		kb.tell("S01 & NW00 & NW02 => W11");
		kb.tell("S01 & NW11 & NW02 => W00");
		kb.tell("S12 & NW02 & NW11 => W22");
		kb.tell("S12 & NW02 & NW22 => W11");
		kb.tell("S12 & NW22 & NW11 => W02");
		kb.tell("S21 & NW22 & NW11 => W20");
		kb.tell("S21 & NW22 & NW20 => W11");
		kb.tell("S21 & NW11 & NW20 => W22");

		// COMBINE LOGIC
		kb.tell("S01 & B10 => (W02 & P20 & NW11 & NP11)");
		kb.tell("B01 & S10 => W20 & P02 & NW11 & NP11");
		kb.tell("S01 & B12 => (W00 & P22 & NW11 & NP11)");
		kb.tell("B01 & S12 => (P00 & W22 & NW11 & NP11)");
		kb.tell("B12 & S21 => (P02 & W20 & NW11 & NP11)");
		kb.tell("S12 & B21 => (W02 & P20 & NW11 & NP11)");
		kb.tell("S10 & B21 => (W00 & P22 & NW11 & NP11)");
		kb.tell("B10 & S21 => (P00 & W22 & NW11 & NP11)");
		kb.tell("S02 & B22  => (W01 & P21 & NW12 & NP12)");
		kb.tell("B02 & S22 => (P01 & W21 & NW12 & NP12)");
		kb.tell("B22 & S20 => (W10 & P12 & NW21 & NP21)");
		kb.tell("B20 & S22 => (P10 & W12 & NW21 & NP21)");
		kb.tell("B00 & S20 => (P01 & W21 & NW10 & NP10)");
		kb.tell("S00 & B20 => (W01 & P21 & NW10 & NP10)");
		kb.tell("B00 & S02 => (P10 & W12 & NW01 & NP01)");
		kb.tell("S00 & B02 => (W10 & P12 & NW01 & NP01)");

		// SB CASES
		kb.tell("SB00 & NW10 & NP10  => WP01");
		kb.tell("SB00 & NW01 & NP01 => WP10");
		kb.tell("SB00 & P10 => W01");
		kb.tell("SB00 & W10 => P01");
		kb.tell("SB00 & W01 => P10");
		kb.tell("SB00 & P01 => W10");

		kb.tell("SB02 & NW12 & NP12  => WP01");
		kb.tell("SB02 & NW01 & NP01 => WP12");
		kb.tell("SB02 & NW12 => W01");
		kb.tell("SB02 & NP12 => P01");
		kb.tell("SB02 & NP01 => P12");
		kb.tell("SB02 & NW01 => W12");

		kb.tell("SB22 & NW12 & NP12 => WP21");
		kb.tell("SB22 & NW21 & NP21 => WP12");
		kb.tell("SB22 & NW12 => W21");
		kb.tell("SB22 & NP12 => P21");
		kb.tell("SB22 & NP21 => P12");
		kb.tell("SB22 & NW21 => W12");

		kb.tell("SB20 & NW10 & NP10  => WP21");
		kb.tell("SB20 & NW21 & NP21=> WP10");
		kb.tell("SB20 & NW10 => W21");
		kb.tell("SB20 & NP21 => P10");
		kb.tell("SB20 & NP10 => P21");
		kb.tell("SB20 & NW21 => W10");

		kb.tell("SB10 & NW00 & NP00 & NW11 & NP11 => WP20");
		kb.tell("SB10 & NW11 & NP11 & NW20 & NP20=> WP00");
		kb.tell("SB10 & NW20 & NP20 & NW00 & NP00 => WP11");
		kb.tell("SB10 & NP00 & NP11 => P20");
		kb.tell("SB10 & NP11 & NP20 => P00");
		kb.tell("SB10 & NP20 & NP00 => P11");
		kb.tell("SB10 & NW00 & NW11 => W20");
		kb.tell("SB10 & NW11 & NW20 => W00");
		kb.tell("SB10 & NW20 & NW00 => W11");

		kb.tell("SB01 & NW00 & NP00 & NW11 & NP11 => WP02");
		kb.tell("SB01 & NW11 & NP11 & NW02 & NP02 => WP00");
		kb.tell("SB01 & NW02 & NP02 & NW00 & NP00 => WP11");
		kb.tell("SB01 & NP00 & NP11 => P02");
		kb.tell("SB01 & NP11 & NP02 => P00");
		kb.tell("SB01 & NP02 & NP00 => P11");
		kb.tell("SB01 & NW00 & NW11 => W02");
		kb.tell("SB01 & NW11 & NW02 => W00");
		kb.tell("SB01 & NW02 & NW00 => W11");

		kb.tell("SB12 & NW02 & NP02 & NW11 & NP11=> WP22");
		kb.tell("SB12 & NW11 & NP11 & NW22 & NP22 => WP02");
		kb.tell("SB12 & NW22 & NP22 & N202 & NP02=> WP11");
		kb.tell("SB12 & NP02 & NP11 => P22");
		kb.tell("SB12 & NP11 & NP22 => P02");
		kb.tell("SB12 & NP22 & NP02 => P11");
		kb.tell("SB12 & NW02 & NW11 => W22");
		kb.tell("SB12 & NW11 & NW22 => W02");
		kb.tell("SB12 & NW22 & NW02 => W11");

		kb.tell("SB21 & NW20 & NP20 & NW11 & NP11 => WP22");
		kb.tell("SB21 & NW11 & NP11 & NW22 & NP22=> WP20");
		kb.tell("SB21 & NW22 & NP22 & NW20 & NP20 => WP11");
		kb.tell("SB21 & NP20 & NP11 => P22");
		kb.tell("SB21 & NP11 & NP22 => P20");
		kb.tell("SB21 & NP22 & NP20 => P11");
		kb.tell("SB21 & NW20 & NW11 => W22");
		kb.tell("SB21 & NW11 & NW22 => W20");
		kb.tell("SB21 & NW22 & NW20 => W11");

		kb.tell("SB11 & NW10 & NP10 & NW01 & NP01 & NW12 & NP12 => WP21");
		kb.tell("SB11 & NW21 & NP21 & NW01 & NP01 & NW12 & NP12 => WP10");
		kb.tell("SB11 & NW10 & NP10 & NW21 & NP21 & NW12 & NP12=> WP01");
		kb.tell("SB11 & NW10 & NP10 & NW01 & NP01 & NW21 & NP21=> WP12");

		kb.tell("SB11 & NP10 & NP01 & NP12 => P21");
		kb.tell("SB11 & NP21 & NP01 & NP12 => P10");
		kb.tell("SB11 & NP10 & NP21 & NP12 => P01");
		kb.tell("SB11 & NP10 & NP01 & NP21 => P12");

		kb.tell("SB11 & NW10 & NW01 & NW12 => P21");
		kb.tell("SB11 & NW21 & NW01 & NW12 => P10");
		kb.tell("SB11 & NW10 & NW21 & NW12 => P01");
		kb.tell("SB11 & NW10 & NW01 & NW21 => P12");

		// KILL CASES
		String[] kill = new String[size * size];
		int index = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				String pos = Integer.toString(i) + Integer.toString(j);
				String str = "K" + pos + " => NW" + pos;
				kill[index++] = str;
			}
		}
		kb.tellAll(kill);

		String[] AHasNWP = new String[size * size];
		index = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				String pos = Integer.toString(i) + Integer.toString(j);
				String str = "A" + pos + " => NW" + pos + " & NP" + pos;
				AHasNWP[index++] = str;
			}
		}
		kb.tellAll(AHasNWP);

	}

	public boolean entail(String str) {
		return (plfce.plfcEntails(kb, new PropositionSymbol(str)));
	}

	public boolean isSafe(int x, int y) {
		String pos = Integer.toString(x) + Integer.toString(y);
		if (entail("NW" + pos) && entail("NP" +pos)) {
			return true;
		}
		return false;
	}

	public boolean hasWumpus(int x, int y) {
		String pos = Integer.toString(x) + Integer.toString(y);
		if (entail("W" + pos) || entail("WP" + pos)) {
			return true;
		}
		return false;
	}

	public boolean hasPit(int x, int y) {
		String pos = Integer.toString(x) + Integer.toString(y);
		if (entail("P" + pos) || entail("WP" + pos)) {
			return true;
		}
		return false;
	}

	public void findPit(int x, int y) {
		String pos = Integer.toString(x) + Integer.toString(y);
		kb.tell("P" + pos);
	}

	public void findWumpus(int x, int y) {
		String pos = Integer.toString(x) + Integer.toString(y);
		kb.tell("W" + pos);
	}

	public boolean hasGlitter(int x, int y) {
		String pos = Integer.toString(x) + Integer.toString(y);
		if (entail("G" + pos)) {
			return true;
		}
		return false;
	}

	public void updateKB(int x, int y, boolean hasBreeze, boolean hasSmell, boolean hasGlitter, boolean hasPit,
			boolean hasWumpus) {
		String pos = Integer.toString(x) + Integer.toString(y);
		if (hasBreeze & hasSmell) {
			kb.tell("SB" + pos);
		} else {
			if (hasBreeze) {
				kb.tell("B" + pos);
			} else if (hasSmell) {
				kb.tell("S" + pos);
			} else {
				kb.tell("A" + pos);
			}
		}
		if (hasGlitter) {
			kb.tell("G" + pos);
		}
		if (!hasPit) {
			kb.tell("NP" + pos);
		} else {
			kb.tell("P" + pos);
		}
		if (!hasWumpus) {
			kb.tell("NW" + pos);
		} else {
			kb.tell("W" + pos);
		}
	}

	public boolean isACell(int x, int y) {
		String pos = Integer.toString(x) + Integer.toString(y);
		if (entail("A" + pos)) {
			return true;
		}
		return false;
	}

	public boolean hasSmell(int x, int y) {
		String pos = Integer.toString(x) + Integer.toString(y);
		if (entail("A" + pos)) {
			return false;
		}
		if (entail("S" + pos) || entail("SB" + pos)) {
			return true;
		}
		return false;
	}

	public boolean hasBreeze(int x, int y) {
		String pos = Integer.toString(x) + Integer.toString(y);
		if (entail("A" + pos)) {
			return false;
		}
		if (entail("B" + pos) || entail("SB" + pos)) {
			return true;
		}
		return false;
	}

	public void kill(int x, int y) {
		String pos = Integer.toString(x) + Integer.toString(y);
		kb.tell("K" + pos);
	}

	public static void main(String[] args) {
		wumpusKnowledgeBase kb = new wumpusKnowledgeBase(3);
		kb.updateKB(0, 0, true, false, false, false, false);
		kb.updateKB(1, 0, false, false, false, false, false);
		kb.updateKB(2, 0, true, false, false, false, false);
		System.out.println(kb.hasPit(0, 1));
		System.out.println(kb.hasPit(2, 1));
	}
	

	public boolean noPit(int x, int y) {
		String pos = Integer.toString(x) + Integer.toString(y);
		if (entail("NP" + pos)) {
			return true;
		} else
			return false;
	}

	public boolean noWumpus(int x, int y) {
		String pos = Integer.toString(x) + Integer.toString(y);
		if (entail("NW" + pos)) {
			return true;
		} else
			return false;
	}
}
