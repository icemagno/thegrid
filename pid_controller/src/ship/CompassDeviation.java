/**
 * 
 */
package ship;

/**
 * @author tony
 *
 */

public class CompassDeviation {

	/**
	 * Purely fictitious Compass Deviation table. This guy needs a new compass!
	 *
	 */
	protected DeviationTableEntry[] deviationTable = { new DeviationTableEntry(0.0, -3.0), new DeviationTableEntry(10.0, -1.5), new DeviationTableEntry(20.0, 0.0), new DeviationTableEntry(30.0, 2.0), new DeviationTableEntry(40.0, 4.0),
			new DeviationTableEntry(50.0, 6.0), new DeviationTableEntry(60.0, 8.0), new DeviationTableEntry(70.0, 10.0), new DeviationTableEntry(80.0, 12.0), new DeviationTableEntry(90.0, 14.0), new DeviationTableEntry(100.0, 12.0),
			new DeviationTableEntry(110.0, 11.0), new DeviationTableEntry(120.0, 10.0), new DeviationTableEntry(130.0, 8.0), new DeviationTableEntry(140.0, 7.0), new DeviationTableEntry(150.0, 6.0), new DeviationTableEntry(160.0, 5.0),
			new DeviationTableEntry(170.0, 4.0), new DeviationTableEntry(180.0, 3.0), new DeviationTableEntry(190.0, 1.5), new DeviationTableEntry(200.0, 0.0), new DeviationTableEntry(210.0, -2.0), new DeviationTableEntry(220.0, -4.0),
			new DeviationTableEntry(230.0, -6.0), new DeviationTableEntry(240.0, -8.0), new DeviationTableEntry(250.0, -10.0), new DeviationTableEntry(260.0, -12.0), new DeviationTableEntry(270.0, -14.0), new DeviationTableEntry(280.0, -12.0),
			new DeviationTableEntry(290.0, -11.0), new DeviationTableEntry(300.0, -10.0), new DeviationTableEntry(310.0, -8.0), new DeviationTableEntry(320.0, -7.0), new DeviationTableEntry(330.0, -6.0), new DeviationTableEntry(340.0, -5.0),
			new DeviationTableEntry(350.0, -4.0), new DeviationTableEntry(360.0, -3.0) };

	public CompassDeviation() {
		/**
		 * Nothing to see here. Deviation table is already set up. If debugging
		 * just dump the deviation table to stdout so we can see it was properly
		 * set up
		 */
	}

	/**
	 * @param hdg
	 * @return double
	 */

	public double FindDeviation(double hdg) {
		/**
		 * If they send us a heading not in the range 0<= hdg <= 360
		 */
		if (hdg < 0.0 || hdg > 360.0) {
			return -99.0;
		}
		/**
		 * Scan down the table and look for the two entries that bracket the
		 * heading we were given. Return the lower one for now. TODO:
		 * Interpolate the values between the lower and upper bracketing
		 * headings and return the interpolated value
		 */
		for (int i = 1; i < deviationTable.length; i++) {
			if (hdg >= deviationTable[i - 1].heading && hdg <= deviationTable[i].heading) {
				return deviationTable[i - 1].deviation;
			}
		}
		// If we ever get here something is sadly wrong
		return -99.0;
	}
}