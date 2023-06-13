/**
 * 
 */
package ship;

/**
 * @author tony
 *
 */

public class CompassDeclination {
	/**
	 * Pop in IGRF declination table values for 2013. This gives us a "good
	 * enough" value for declination for the simulation. Values are stored for
	 * every 10 degrees and we take the one that's close enough when we return a
	 * result.
	 */
	protected DeclinationTableEntry[] declinationTable = { new DeclinationTableEntry(80, -180, 5.84566), new DeclinationTableEntry(80, -170, 11.89447), new DeclinationTableEntry(80, -160, 17.67551), new DeclinationTableEntry(80, -150, 22.66466),
			new DeclinationTableEntry(80, -140, 25.9251), new DeclinationTableEntry(80, -130, 25.29135), new DeclinationTableEntry(80, -120, 14.83508), new DeclinationTableEntry(80, -110, -16.06057), new DeclinationTableEntry(80, -100, -47.57007),
			new DeclinationTableEntry(80, -90, -58.37613), new DeclinationTableEntry(80, -80, -58.90307), new DeclinationTableEntry(80, -70, -55.27729), new DeclinationTableEntry(80, -60, -49.7103), new DeclinationTableEntry(80, -50, -43.11064),
			new DeclinationTableEntry(80, -40, -35.91437), new DeclinationTableEntry(80, -30, -28.36089), new DeclinationTableEntry(80, -20, -20.59892), new DeclinationTableEntry(80, -10, -12.73316), new DeclinationTableEntry(80, 0, -4.84742),
			new DeclinationTableEntry(80, 10, 2.98189), new DeclinationTableEntry(80, 20, 10.67523), new DeclinationTableEntry(80, 30, 18.13808), new DeclinationTableEntry(80, 40, 25.24393), new DeclinationTableEntry(80, 50, 31.80621),
			new DeclinationTableEntry(80, 60, 37.52743), new DeclinationTableEntry(80, 70, 41.89914), new DeclinationTableEntry(80, 80, 43.99856), new DeclinationTableEntry(80, 90, 42.11791), new DeclinationTableEntry(80, 100, 33.56063),
			new DeclinationTableEntry(80, 110, 17.3085), new DeclinationTableEntry(80, 120, 0.50705), new DeclinationTableEntry(80, 130, -8.87387), new DeclinationTableEntry(80, 140, -11.26829), new DeclinationTableEntry(80, 150, -9.47512),
			new DeclinationTableEntry(80, 160, -5.38363), new DeclinationTableEntry(80, 170, -0.06319), new DeclinationTableEntry(80, 180, 5.84566), new DeclinationTableEntry(70, -180, 4.10214), new DeclinationTableEntry(70, -170, 9.9663),
			new DeclinationTableEntry(70, -160, 15.44309), new DeclinationTableEntry(70, -150, 20.19486), new DeclinationTableEntry(70, -140, 23.72252), new DeclinationTableEntry(70, -130, 25.16915), new DeclinationTableEntry(70, -120, 22.95008),
			new DeclinationTableEntry(70, -110, 14.34894), new DeclinationTableEntry(70, -100, -2.62088), new DeclinationTableEntry(70, -90, -21.8204), new DeclinationTableEntry(70, -80, -33.63478), new DeclinationTableEntry(70, -70, -37.62803),
			new DeclinationTableEntry(70, -60, -36.86416), new DeclinationTableEntry(70, -50, -33.46967), new DeclinationTableEntry(70, -40, -28.6017), new DeclinationTableEntry(70, -30, -22.89672), new DeclinationTableEntry(70, -20, -16.73347),
			new DeclinationTableEntry(70, -10, -10.36467), new DeclinationTableEntry(70, 0, -3.97703), new DeclinationTableEntry(70, 10, 2.28674), new DeclinationTableEntry(70, 20, 8.31334), new DeclinationTableEntry(70, 30, 13.986),
			new DeclinationTableEntry(70, 40, 19.12772), new DeclinationTableEntry(70, 50, 23.43324), new DeclinationTableEntry(70, 60, 26.39534), new DeclinationTableEntry(70, 70, 27.21046), new DeclinationTableEntry(70, 80, 24.6896),
			new DeclinationTableEntry(70, 90, 17.52406), new DeclinationTableEntry(70, 100, 5.93071), new DeclinationTableEntry(70, 110, -6.17951), new DeclinationTableEntry(70, 120, -14.16925), new DeclinationTableEntry(70, 130, -17.02932),
			new DeclinationTableEntry(70, 140, -16.00795), new DeclinationTableEntry(70, 150, -12.50523), new DeclinationTableEntry(70, 160, -7.55119), new DeclinationTableEntry(70, 170, -1.85338), new DeclinationTableEntry(70, 180, 4.10214),
			new DeclinationTableEntry(60, -180, 3.81147), new DeclinationTableEntry(60, -170, 8.99017), new DeclinationTableEntry(60, -160, 13.70841), new DeclinationTableEntry(60, -150, 17.64329), new DeclinationTableEntry(60, -140, 20.363),
			new DeclinationTableEntry(60, -130, 21.28646), new DeclinationTableEntry(60, -120, 19.60486), new DeclinationTableEntry(60, -110, 14.23326), new DeclinationTableEntry(60, -100, 4.40987), new DeclinationTableEntry(60, -90, -8.21863),
			new DeclinationTableEntry(60, -80, -19.05151), new DeclinationTableEntry(60, -70, -25.0271), new DeclinationTableEntry(60, -60, -26.48128), new DeclinationTableEntry(60, -50, -24.8883), new DeclinationTableEntry(60, -40, -21.4883),
			new DeclinationTableEntry(60, -30, -17.09604), new DeclinationTableEntry(60, -20, -12.2242), new DeclinationTableEntry(60, -10, -7.2298), new DeclinationTableEntry(60, 0, -2.39239), new DeclinationTableEntry(60, 10, 2.1036),
			new DeclinationTableEntry(60, 20, 6.20326), new DeclinationTableEntry(60, 30, 9.91673), new DeclinationTableEntry(60, 40, 13.16466), new DeclinationTableEntry(60, 50, 15.66342), new DeclinationTableEntry(60, 60, 16.93021),
			new DeclinationTableEntry(60, 70, 16.36833), new DeclinationTableEntry(60, 80, 13.40044), new DeclinationTableEntry(60, 90, 7.77112), new DeclinationTableEntry(60, 100, 0.15851), new DeclinationTableEntry(60, 110, -7.47674),
			new DeclinationTableEntry(60, 120, -12.90934), new DeclinationTableEntry(60, 130, -15.10024), new DeclinationTableEntry(60, 140, -14.27346), new DeclinationTableEntry(60, 150, -11.2023), new DeclinationTableEntry(60, 160, -6.72298),
			new DeclinationTableEntry(60, 170, -1.54459), new DeclinationTableEntry(60, 180, 3.81147), new DeclinationTableEntry(50, -180, 4.12469), new DeclinationTableEntry(50, -170, 8.55805), new DeclinationTableEntry(50, -160, 12.46656),
			new DeclinationTableEntry(50, -150, 15.57267), new DeclinationTableEntry(50, -140, 17.53137), new DeclinationTableEntry(50, -130, 17.97249), new DeclinationTableEntry(50, -120, 16.49058), new DeclinationTableEntry(50, -110, 12.55215),
			new DeclinationTableEntry(50, -100, 5.68321), new DeclinationTableEntry(50, -90, -3.5025), new DeclinationTableEntry(50, -80, -12.41619), new DeclinationTableEntry(50, -70, -18.26569), new DeclinationTableEntry(50, -60, -20.30752),
			new DeclinationTableEntry(50, -50, -19.36861), new DeclinationTableEntry(50, -40, -16.59481), new DeclinationTableEntry(50, -30, -12.87846), new DeclinationTableEntry(50, -20, -8.7794), new DeclinationTableEntry(50, -10, -4.69192),
			new DeclinationTableEntry(50, 0, -0.98086), new DeclinationTableEntry(50, 10, 2.12701), new DeclinationTableEntry(50, 20, 4.68146), new DeclinationTableEntry(50, 30, 6.84843), new DeclinationTableEntry(50, 40, 8.62116),
			new DeclinationTableEntry(50, 50, 9.77842), new DeclinationTableEntry(50, 60, 10.04568), new DeclinationTableEntry(50, 70, 9.1948), new DeclinationTableEntry(50, 80, 7.04684), new DeclinationTableEntry(50, 90, 3.52067),
			new DeclinationTableEntry(50, 100, -1.16138), new DeclinationTableEntry(50, 110, -6.1811), new DeclinationTableEntry(50, 120, -10.20748), new DeclinationTableEntry(50, 130, -12.12283), new DeclinationTableEntry(50, 140, -11.61836),
			new DeclinationTableEntry(50, 150, -9.07404), new DeclinationTableEntry(50, 160, -5.16163), new DeclinationTableEntry(50, 170, -0.57397), new DeclinationTableEntry(50, 180, 4.12469), new DeclinationTableEntry(40, -180, 5.21564),
			new DeclinationTableEntry(40, -170, 8.6606), new DeclinationTableEntry(40, -160, 11.49209), new DeclinationTableEntry(40, -150, 13.66905), new DeclinationTableEntry(40, -140, 15.01223), new DeclinationTableEntry(40, -130, 15.23888),
			new DeclinationTableEntry(40, -120, 14.04225), new DeclinationTableEntry(40, -110, 11.02223), new DeclinationTableEntry(40, -100, 5.75009), new DeclinationTableEntry(40, -90, -1.54171), new DeclinationTableEntry(40, -80, -9.1497),
			new DeclinationTableEntry(40, -70, -14.71922), new DeclinationTableEntry(40, -60, -17.08382), new DeclinationTableEntry(40, -50, -16.56848), new DeclinationTableEntry(40, -40, -14.15935), new DeclinationTableEntry(40, -30, -10.7924),
			new DeclinationTableEntry(40, -20, -7.0566), new DeclinationTableEntry(40, -10, -3.38561), new DeclinationTableEntry(40, 0, -0.27517), new DeclinationTableEntry(40, 10, 2.01093), new DeclinationTableEntry(40, 20, 3.68027),
			new DeclinationTableEntry(40, 30, 4.95897), new DeclinationTableEntry(40, 40, 5.71383), new DeclinationTableEntry(40, 50, 5.79899), new DeclinationTableEntry(40, 60, 5.36168), new DeclinationTableEntry(40, 70, 4.55764),
			new DeclinationTableEntry(40, 80, 3.29286), new DeclinationTableEntry(40, 90, 1.35689), new DeclinationTableEntry(40, 100, -1.36542), new DeclinationTableEntry(40, 110, -4.62345), new DeclinationTableEntry(40, 120, -7.54326),
			new DeclinationTableEntry(40, 130, -9.01993), new DeclinationTableEntry(40, 140, -8.52373), new DeclinationTableEntry(40, 150, -6.25579), new DeclinationTableEntry(40, 160, -2.76951), new DeclinationTableEntry(40, 170, 1.26919),
			new DeclinationTableEntry(40, 180, 5.21564), new DeclinationTableEntry(30, -180, 6.75649), new DeclinationTableEntry(30, -170, 8.92351), new DeclinationTableEntry(30, -160, 10.46589), new DeclinationTableEntry(30, -150, 11.73188),
			new DeclinationTableEntry(30, -140, 12.64296), new DeclinationTableEntry(30, -130, 12.81547), new DeclinationTableEntry(30, -120, 11.91139), new DeclinationTableEntry(30, -110, 9.60175), new DeclinationTableEntry(30, -100, 5.48259),
			new DeclinationTableEntry(30, -90, -0.45388), new DeclinationTableEntry(30, -80, -7.1263), new DeclinationTableEntry(30, -70, -12.65205), new DeclinationTableEntry(30, -60, -15.63459), new DeclinationTableEntry(30, -50, -15.82028),
			new DeclinationTableEntry(30, -40, -13.83592), new DeclinationTableEntry(30, -30, -10.62022), new DeclinationTableEntry(30, -20, -6.89806), new DeclinationTableEntry(30, -10, -3.25742), new DeclinationTableEntry(30, 0, -0.36885),
			new DeclinationTableEntry(30, 10, 1.54746), new DeclinationTableEntry(30, 20, 2.92688), new DeclinationTableEntry(30, 30, 3.91185), new DeclinationTableEntry(30, 40, 4.05247), new DeclinationTableEntry(30, 50, 3.30949),
			new DeclinationTableEntry(30, 60, 2.35674), new DeclinationTableEntry(30, 70, 1.63193), new DeclinationTableEntry(30, 80, 0.9672), new DeclinationTableEntry(30, 90, 0.08647), new DeclinationTableEntry(30, 100, -1.23347),
			new DeclinationTableEntry(30, 110, -3.10994), new DeclinationTableEntry(30, 120, -5.02795), new DeclinationTableEntry(30, 130, -5.91573), new DeclinationTableEntry(30, 140, -5.16037), new DeclinationTableEntry(30, 150, -2.94239),
			new DeclinationTableEntry(30, 160, 0.24256), new DeclinationTableEntry(30, 170, 3.73263), new DeclinationTableEntry(30, 180, 6.75649), new DeclinationTableEntry(20, -180, 8.08456), new DeclinationTableEntry(20, -170, 8.98716),
			new DeclinationTableEntry(20, -160, 9.43715), new DeclinationTableEntry(20, -150, 10.02254), new DeclinationTableEntry(20, -140, 10.59505), new DeclinationTableEntry(20, -130, 10.67516), new DeclinationTableEntry(20, -120, 10.04252),
			new DeclinationTableEntry(20, -110, 8.47501), new DeclinationTableEntry(20, -100, 5.45403), new DeclinationTableEntry(20, -90, 0.64368), new DeclinationTableEntry(20, -80, -5.40296), new DeclinationTableEntry(20, -70, -11.21055),
			new DeclinationTableEntry(20, -60, -15.22481), new DeclinationTableEntry(20, -50, -16.5446), new DeclinationTableEntry(20, -40, -15.18794), new DeclinationTableEntry(20, -30, -11.99673), new DeclinationTableEntry(20, -20, -8.00061),
			new DeclinationTableEntry(20, -10, -4.12085), new DeclinationTableEntry(20, 0, -1.19372), new DeclinationTableEntry(20, 10, 0.70751), new DeclinationTableEntry(20, 20, 2.21799), new DeclinationTableEntry(20, 30, 3.26551),
			new DeclinationTableEntry(20, 40, 3.07195), new DeclinationTableEntry(20, 50, 1.76373), new DeclinationTableEntry(20, 60, 0.45445), new DeclinationTableEntry(20, 70, -0.2686), new DeclinationTableEntry(20, 80, -0.61113),
			new DeclinationTableEntry(20, 90, -0.75959), new DeclinationTableEntry(20, 100, -0.95552), new DeclinationTableEntry(20, 110, -1.67682), new DeclinationTableEntry(20, 120, -2.7139), new DeclinationTableEntry(20, 130, -2.9725),
			new DeclinationTableEntry(20, 140, -1.83789), new DeclinationTableEntry(20, 150, 0.40054), new DeclinationTableEntry(20, 160, 3.26644), new DeclinationTableEntry(20, 170, 6.1054), new DeclinationTableEntry(20, 180, 8.08456),
			new DeclinationTableEntry(10, -180, 8.84303), new DeclinationTableEntry(10, -170, 8.92874), new DeclinationTableEntry(10, -160, 8.83194), new DeclinationTableEntry(10, -150, 9.06899), new DeclinationTableEntry(10, -140, 9.36758),
			new DeclinationTableEntry(10, -130, 9.30334), new DeclinationTableEntry(10, -120, 8.91046), new DeclinationTableEntry(10, -110, 8.06296), new DeclinationTableEntry(10, -100, 6.02429), new DeclinationTableEntry(10, -90, 2.0803),
			new DeclinationTableEntry(10, -80, -3.66864), new DeclinationTableEntry(10, -70, -10.01889), new DeclinationTableEntry(10, -60, -15.2663), new DeclinationTableEntry(10, -50, -18.01232), new DeclinationTableEntry(10, -40, -17.62456),
			new DeclinationTableEntry(10, -30, -14.62284), new DeclinationTableEntry(10, -20, -10.30688), new DeclinationTableEntry(10, -10, -6.0335), new DeclinationTableEntry(10, 0, -2.78367), new DeclinationTableEntry(10, 10, -0.5086),
			new DeclinationTableEntry(10, 20, 1.41198), new DeclinationTableEntry(10, 30, 2.59318), new DeclinationTableEntry(10, 40, 2.09948), new DeclinationTableEntry(10, 50, 0.34792), new DeclinationTableEntry(10, 60, -1.2251),
			new DeclinationTableEntry(10, 70, -1.95712), new DeclinationTableEntry(10, 80, -1.99842), new DeclinationTableEntry(10, 90, -1.41112), new DeclinationTableEntry(10, 100, -0.56173), new DeclinationTableEntry(10, 110, -0.34349),
			new DeclinationTableEntry(10, 120, -0.7252), new DeclinationTableEntry(10, 130, -0.46128), new DeclinationTableEntry(10, 140, 1.01655), new DeclinationTableEntry(10, 150, 3.21937), new DeclinationTableEntry(10, 160, 5.67198),
			new DeclinationTableEntry(10, 170, 7.78203), new DeclinationTableEntry(10, 180, 8.84303), new DeclinationTableEntry(0, -180, 9.44306), new DeclinationTableEntry(0, -170, 9.30881), new DeclinationTableEntry(0, -160, 9.17015),
			new DeclinationTableEntry(0, -150, 9.32495), new DeclinationTableEntry(0, -140, 9.45961), new DeclinationTableEntry(0, -130, 9.26604), new DeclinationTableEntry(0, -120, 8.9591), new DeclinationTableEntry(0, -110, 8.5314),
			new DeclinationTableEntry(0, -100, 7.16867), new DeclinationTableEntry(0, -90, 3.84185), new DeclinationTableEntry(0, -80, -1.81919), new DeclinationTableEntry(0, -70, -8.83128), new DeclinationTableEntry(0, -60, -15.26606),
			new DeclinationTableEntry(0, -50, -19.45119), new DeclinationTableEntry(0, -40, -20.42142), new DeclinationTableEntry(0, -30, -18.25135), new DeclinationTableEntry(0, -20, -14.12641), new DeclinationTableEntry(0, -10, -9.57276),
			new DeclinationTableEntry(0, 0, -5.63834), new DeclinationTableEntry(0, 10, -2.4415), new DeclinationTableEntry(0, 20, 0.15294), new DeclinationTableEntry(0, 30, 1.32601), new DeclinationTableEntry(0, 40, 0.27436),
			new DeclinationTableEntry(0, 50, -2.03349), new DeclinationTableEntry(0, 60, -3.82117), new DeclinationTableEntry(0, 70, -4.39333), new DeclinationTableEntry(0, 80, -3.86687), new DeclinationTableEntry(0, 90, -2.29087),
			new DeclinationTableEntry(0, 100, -0.32024), new DeclinationTableEntry(0, 110, 0.7184), new DeclinationTableEntry(0, 120, 0.81436), new DeclinationTableEntry(0, 130, 1.43466), new DeclinationTableEntry(0, 140, 3.11848),
			new DeclinationTableEntry(0, 150, 5.23213), new DeclinationTableEntry(0, 160, 7.3155), new DeclinationTableEntry(0, 170, 8.87651), new DeclinationTableEntry(0, 180, 9.44306), new DeclinationTableEntry(-10, -180, 10.61755),
			new DeclinationTableEntry(-10, -170, 10.64968), new DeclinationTableEntry(-10, -160, 10.70881), new DeclinationTableEntry(-10, -150, 10.90264), new DeclinationTableEntry(-10, -140, 10.95363), new DeclinationTableEntry(-10, -130, 10.65515),
			new DeclinationTableEntry(-10, -120, 10.25972), new DeclinationTableEntry(-10, -110, 9.8964), new DeclinationTableEntry(-10, -100, 8.89406), new DeclinationTableEntry(-10, -90, 6.03645), new DeclinationTableEntry(-10, -80, 0.46054),
			new DeclinationTableEntry(-10, -70, -7.17564), new DeclinationTableEntry(-10, -60, -14.7299), new DeclinationTableEntry(-10, -50, -20.24029), new DeclinationTableEntry(-10, -40, -22.74699), new DeclinationTableEntry(-10, -30, -22.21945),
			new DeclinationTableEntry(-10, -20, -19.44421), new DeclinationTableEntry(-10, -10, -15.3469), new DeclinationTableEntry(-10, 0, -10.61297), new DeclinationTableEntry(-10, 10, -5.99233), new DeclinationTableEntry(-10, 20, -2.62225),
			new DeclinationTableEntry(-10, 30, -1.93651), new DeclinationTableEntry(-10, 40, -4.09088), new DeclinationTableEntry(-10, 50, -7.09808), new DeclinationTableEntry(-10, 60, -8.82678), new DeclinationTableEntry(-10, 70, -8.75151),
			new DeclinationTableEntry(-10, 80, -7.15518), new DeclinationTableEntry(-10, 90, -4.21729), new DeclinationTableEntry(-10, 100, -0.92367), new DeclinationTableEntry(-10, 110, 1.06197), new DeclinationTableEntry(-10, 120, 1.75392),
			new DeclinationTableEntry(-10, 130, 2.74602), new DeclinationTableEntry(-10, 140, 4.60805), new DeclinationTableEntry(-10, 150, 6.74084), new DeclinationTableEntry(-10, 160, 8.71254), new DeclinationTableEntry(-10, 170, 10.09802),
			new DeclinationTableEntry(-10, 180, 10.61755), new DeclinationTableEntry(-20, -180, 12.86407), new DeclinationTableEntry(-20, -170, 13.17427), new DeclinationTableEntry(-20, -160, 13.43557), new DeclinationTableEntry(-20, -150, 13.64988),
			new DeclinationTableEntry(-20, -140, 13.61182), new DeclinationTableEntry(-20, -130, 13.24444), new DeclinationTableEntry(-20, -120, 12.80104), new DeclinationTableEntry(-20, -110, 12.45015), new DeclinationTableEntry(-20, -100, 11.65106),
			new DeclinationTableEntry(-20, -90, 9.12651), new DeclinationTableEntry(-20, -80, 3.69671), new DeclinationTableEntry(-20, -70, -4.36694), new DeclinationTableEntry(-20, -60, -12.95121), new DeclinationTableEntry(-20, -50, -19.70719),
			new DeclinationTableEntry(-20, -40, -23.59814), new DeclinationTableEntry(-20, -30, -24.88825), new DeclinationTableEntry(-20, -20, -24.31494), new DeclinationTableEntry(-20, -10, -21.96995), new DeclinationTableEntry(-20, 0, -17.59593),
			new DeclinationTableEntry(-20, 10, -12.4354), new DeclinationTableEntry(-20, 20, -9.40918), new DeclinationTableEntry(-20, 30, -10.35745), new DeclinationTableEntry(-20, 40, -13.9389), new DeclinationTableEntry(-20, 50, -17.14401),
			new DeclinationTableEntry(-20, 60, -18.056), new DeclinationTableEntry(-20, 70, -16.59347), new DeclinationTableEntry(-20, 80, -13.2888), new DeclinationTableEntry(-20, 90, -8.5032), new DeclinationTableEntry(-20, 100, -3.46237),
			new DeclinationTableEntry(-20, 110, -0.00539), new DeclinationTableEntry(-20, 120, 1.85638), new DeclinationTableEntry(-20, 130, 3.63612), new DeclinationTableEntry(-20, 140, 5.95836), new DeclinationTableEntry(-20, 150, 8.40771),
			new DeclinationTableEntry(-20, 160, 10.59288), new DeclinationTableEntry(-20, 170, 12.13001), new DeclinationTableEntry(-20, 180, 12.86407), new DeclinationTableEntry(-30, -180, 16.5022), new DeclinationTableEntry(-30, -170, 17.02446),
			new DeclinationTableEntry(-30, -160, 17.33447), new DeclinationTableEntry(-30, -150, 17.44754), new DeclinationTableEntry(-30, -140, 17.2996), new DeclinationTableEntry(-30, -130, 16.99464), new DeclinationTableEntry(-30, -120, 16.7795),
			new DeclinationTableEntry(-30, -110, 16.66288), new DeclinationTableEntry(-30, -100, 16.00569), new DeclinationTableEntry(-30, -90, 13.56343), new DeclinationTableEntry(-30, -80, 8.20395), new DeclinationTableEntry(-30, -70, 0.00295),
			new DeclinationTableEntry(-30, -60, -9.22746), new DeclinationTableEntry(-30, -50, -17.00712), new DeclinationTableEntry(-30, -40, -22.00754), new DeclinationTableEntry(-30, -30, -24.54355), new DeclinationTableEntry(-30, -20, -25.52989),
			new DeclinationTableEntry(-30, -10, -25.0981), new DeclinationTableEntry(-30, 0, -22.95349), new DeclinationTableEntry(-30, 10, -20.58691), new DeclinationTableEntry(-30, 20, -20.95144), new DeclinationTableEntry(-30, 30, -24.48534),
			new DeclinationTableEntry(-30, 40, -28.79989), new DeclinationTableEntry(-30, 50, -31.56588), new DeclinationTableEntry(-30, 60, -31.68097), new DeclinationTableEntry(-30, 70, -29.07988), new DeclinationTableEntry(-30, 80, -24.10425),
			new DeclinationTableEntry(-30, 90, -17.16427), new DeclinationTableEntry(-30, 100, -9.63514), new DeclinationTableEntry(-30, 110, -3.57861), new DeclinationTableEntry(-30, 120, 0.63272), new DeclinationTableEntry(-30, 130, 4.14974),
			new DeclinationTableEntry(-30, 140, 7.60854), new DeclinationTableEntry(-30, 150, 10.84033), new DeclinationTableEntry(-30, 160, 13.55569), new DeclinationTableEntry(-30, 170, 15.46065), new DeclinationTableEntry(-30, 180, 16.5022),
			new DeclinationTableEntry(-40, -180, 21.93228), new DeclinationTableEntry(-40, -170, 22.4797), new DeclinationTableEntry(-40, -160, 22.63251), new DeclinationTableEntry(-40, -150, 22.55537), new DeclinationTableEntry(-40, -140, 22.38242),
			new DeclinationTableEntry(-40, -130, 22.32094), new DeclinationTableEntry(-40, -120, 22.47636), new DeclinationTableEntry(-40, -110, 22.54425), new DeclinationTableEntry(-40, -100, 21.71055), new DeclinationTableEntry(-40, -90, 18.92924),
			new DeclinationTableEntry(-40, -80, 13.47257), new DeclinationTableEntry(-40, -70, 5.53633), new DeclinationTableEntry(-40, -60, -3.45919), new DeclinationTableEntry(-40, -50, -11.45911), new DeclinationTableEntry(-40, -40, -17.08391),
			new DeclinationTableEntry(-40, -30, -20.31049), new DeclinationTableEntry(-40, -20, -21.92577), new DeclinationTableEntry(-40, -10, -22.63332), new DeclinationTableEntry(-40, 0, -23.21495), new DeclinationTableEntry(-40, 10, -25.19159),
			new DeclinationTableEntry(-40, 20, -29.53622), new DeclinationTableEntry(-40, 30, -35.22079), new DeclinationTableEntry(-40, 40, -40.47367), new DeclinationTableEntry(-40, 50, -44.09743), new DeclinationTableEntry(-40, 60, -45.44037),
			new DeclinationTableEntry(-40, 70, -44.11617), new DeclinationTableEntry(-40, 80, -39.80317), new DeclinationTableEntry(-40, 90, -32.25744), new DeclinationTableEntry(-40, 100, -22.21859), new DeclinationTableEntry(-40, 110, -11.96062),
			new DeclinationTableEntry(-40, 120, -3.24226), new DeclinationTableEntry(-40, 130, 3.90447), new DeclinationTableEntry(-40, 140, 9.84474), new DeclinationTableEntry(-40, 150, 14.64561), new DeclinationTableEntry(-40, 160, 18.264),
			new DeclinationTableEntry(-40, 170, 20.64983), new DeclinationTableEntry(-40, 180, 21.93228), new DeclinationTableEntry(-50, -180, 30.32998), new DeclinationTableEntry(-50, -170, 30.59055), new DeclinationTableEntry(-50, -160, 30.4153),
			new DeclinationTableEntry(-50, -150, 30.12363), new DeclinationTableEntry(-50, -140, 29.9226), new DeclinationTableEntry(-50, -130, 29.89607), new DeclinationTableEntry(-50, -120, 29.86813), new DeclinationTableEntry(-50, -110, 29.32161),
			new DeclinationTableEntry(-50, -100, 27.55206), new DeclinationTableEntry(-50, -90, 23.97482), new DeclinationTableEntry(-50, -80, 18.40094), new DeclinationTableEntry(-50, -70, 11.22435), new DeclinationTableEntry(-50, -60, 3.44672),
			new DeclinationTableEntry(-50, -50, -3.64857), new DeclinationTableEntry(-50, -40, -9.12294), new DeclinationTableEntry(-50, -30, -12.84154), new DeclinationTableEntry(-50, -20, -15.41953), new DeclinationTableEntry(-50, -10, -17.84583),
			new DeclinationTableEntry(-50, 0, -21.16571), new DeclinationTableEntry(-50, 10, -26.1025), new DeclinationTableEntry(-50, 20, -32.52821), new DeclinationTableEntry(-50, 30, -39.51321), new DeclinationTableEntry(-50, 40, -46.0275),
			new DeclinationTableEntry(-50, 50, -51.38392), new DeclinationTableEntry(-50, 60, -55.17676), new DeclinationTableEntry(-50, 70, -57.07228), new DeclinationTableEntry(-50, 80, -56.59315), new DeclinationTableEntry(-50, 90, -52.88959),
			new DeclinationTableEntry(-50, 100, -44.7498), new DeclinationTableEntry(-50, 110, -31.49367), new DeclinationTableEntry(-50, 120, -14.85885), new DeclinationTableEntry(-50, 130, 1.02802), new DeclinationTableEntry(-50, 140, 13.20543),
			new DeclinationTableEntry(-50, 150, 21.38116), new DeclinationTableEntry(-50, 160, 26.40533), new DeclinationTableEntry(-50, 170, 29.14595), new DeclinationTableEntry(-50, 180, 30.32998), new DeclinationTableEntry(-60, -180, 46.70841),
			new DeclinationTableEntry(-60, -170, 45.57387), new DeclinationTableEntry(-60, -160, 44.26946), new DeclinationTableEntry(-60, -150, 43.01235), new DeclinationTableEntry(-60, -140, 41.83321), new DeclinationTableEntry(-60, -130, 40.59247),
			new DeclinationTableEntry(-60, -120, 38.99506), new DeclinationTableEntry(-60, -110, 36.66669), new DeclinationTableEntry(-60, -100, 33.28942), new DeclinationTableEntry(-60, -90, 28.72578), new DeclinationTableEntry(-60, -80, 23.0848),
			new DeclinationTableEntry(-60, -70, 16.72977), new DeclinationTableEntry(-60, -60, 10.21405), new DeclinationTableEntry(-60, -50, 4.1166), new DeclinationTableEntry(-60, -40, -1.19214), new DeclinationTableEntry(-60, -30, -5.73318),
			new DeclinationTableEntry(-60, -20, -9.92462), new DeclinationTableEntry(-60, -10, -14.3921), new DeclinationTableEntry(-60, 0, -19.68676), new DeclinationTableEntry(-60, 10, -26.0395), new DeclinationTableEntry(-60, 20, -33.26471),
			new DeclinationTableEntry(-60, 30, -40.88097), new DeclinationTableEntry(-60, 40, -48.35851), new DeclinationTableEntry(-60, 50, -55.3009), new DeclinationTableEntry(-60, 60, -61.4778), new DeclinationTableEntry(-60, 70, -66.76157),
			new DeclinationTableEntry(-60, 80, -71.028), new DeclinationTableEntry(-60, 90, -74.02547), new DeclinationTableEntry(-60, 100, -75.14204), new DeclinationTableEntry(-60, 110, -72.74346), new DeclinationTableEntry(-60, 120, -61.53014),
			new DeclinationTableEntry(-60, 130, -24.64977), new DeclinationTableEntry(-60, 140, 22.94142), new DeclinationTableEntry(-60, 150, 40.711), new DeclinationTableEntry(-60, 160, 46.0269), new DeclinationTableEntry(-60, 170, 47.19225),
			new DeclinationTableEntry(-60, 180, 46.70841), new DeclinationTableEntry(-70, -180, 85.37702), new DeclinationTableEntry(-70, -170, 77.46655), new DeclinationTableEntry(-70, -160, 71.16845), new DeclinationTableEntry(-70, -150, 65.81107),
			new DeclinationTableEntry(-70, -140, 60.96249), new DeclinationTableEntry(-70, -130, 56.29653), new DeclinationTableEntry(-70, -120, 51.5531), new DeclinationTableEntry(-70, -110, 46.54015), new DeclinationTableEntry(-70, -100, 41.14901),
			new DeclinationTableEntry(-70, -90, 35.36534), new DeclinationTableEntry(-70, -80, 29.26581), new DeclinationTableEntry(-70, -70, 22.99625), new DeclinationTableEntry(-70, -60, 16.72956), new DeclinationTableEntry(-70, -50, 10.60705),
			new DeclinationTableEntry(-70, -40, 4.67912), new DeclinationTableEntry(-70, -30, -1.12815), new DeclinationTableEntry(-70, -20, -6.99728), new DeclinationTableEntry(-70, -10, -13.15451), new DeclinationTableEntry(-70, 0, -19.78353),
			new DeclinationTableEntry(-70, 10, -26.95812), new DeclinationTableEntry(-70, 20, -34.62304), new DeclinationTableEntry(-70, 30, -42.62748), new DeclinationTableEntry(-70, 40, -50.78957), new DeclinationTableEntry(-70, 50, -58.95965),
			new DeclinationTableEntry(-70, 60, -67.06006), new DeclinationTableEntry(-70, 70, -75.10101), new DeclinationTableEntry(-70, 80, -83.18776), new DeclinationTableEntry(-70, 90, -91.5419), new DeclinationTableEntry(-70, 100, -100.57094),
			new DeclinationTableEntry(-70, 110, -111.05781), new DeclinationTableEntry(-70, 120, -124.66342), new DeclinationTableEntry(-70, 130, -145.15806), new DeclinationTableEntry(-70, 140, -178.77316), new DeclinationTableEntry(-70, 150, 140.70475),
			new DeclinationTableEntry(-70, 160, 112.6885), new DeclinationTableEntry(-70, 170, 96.17595), new DeclinationTableEntry(-70, 180, 85.37702), new DeclinationTableEntry(-80, -180, 130.6841), new DeclinationTableEntry(-80, -170, 118.26279),
			new DeclinationTableEntry(-80, -160, 106.9858), new DeclinationTableEntry(-80, -150, 96.7024), new DeclinationTableEntry(-80, -140, 87.22027), new DeclinationTableEntry(-80, -130, 78.35005), new DeclinationTableEntry(-80, -120, 69.92704),
			new DeclinationTableEntry(-80, -110, 61.81932), new DeclinationTableEntry(-80, -100, 53.92883), new DeclinationTableEntry(-80, -90, 46.188), new DeclinationTableEntry(-80, -80, 38.55338), new DeclinationTableEntry(-80, -70, 30.99701),
			new DeclinationTableEntry(-80, -60, 23.49684), new DeclinationTableEntry(-80, -50, 16.02769), new DeclinationTableEntry(-80, -40, 8.55507), new DeclinationTableEntry(-80, -30, 1.03354), new DeclinationTableEntry(-80, -20, -6.58976),
			new DeclinationTableEntry(-80, -10, -14.3677), new DeclinationTableEntry(-80, 0, -22.34468), new DeclinationTableEntry(-80, 10, -30.55032), new DeclinationTableEntry(-80, 20, -38.99797), new DeclinationTableEntry(-80, 30, -47.68863),
			new DeclinationTableEntry(-80, 40, -56.61931), new DeclinationTableEntry(-80, 50, -65.79339), new DeclinationTableEntry(-80, 60, -75.2308), new DeclinationTableEntry(-80, 70, -84.97637), new DeclinationTableEntry(-80, 80, -95.10549),
			new DeclinationTableEntry(-80, 90, -105.72626), new DeclinationTableEntry(-80, 100, -116.97602), new DeclinationTableEntry(-80, 110, -129.00712), new DeclinationTableEntry(-80, 120, -141.95275), new DeclinationTableEntry(-80, 130, -155.86372),
			new DeclinationTableEntry(-80, 140, -170.62258), new DeclinationTableEntry(-80, 150, 174.11859), new DeclinationTableEntry(-80, 160, 158.89894), new DeclinationTableEntry(-80, 170, 144.28501), new DeclinationTableEntry(-80, 180, 130.6841),
			new DeclinationTableEntry(-90, -180, 150.31365), new DeclinationTableEntry(-90, -170, 140.31341), new DeclinationTableEntry(-90, -160, 130.31322), new DeclinationTableEntry(-90, -150, 120.31308), new DeclinationTableEntry(-90, -140, 110.31298),
			new DeclinationTableEntry(-90, -130, 100.31295), new DeclinationTableEntry(-90, -120, 90.31296), new DeclinationTableEntry(-90, -110, 80.31304), new DeclinationTableEntry(-90, -100, 70.31317), new DeclinationTableEntry(-90, -90, 60.31334),
			new DeclinationTableEntry(-90, -80, 50.31356), new DeclinationTableEntry(-90, -70, 40.31382), new DeclinationTableEntry(-90, -60, 30.31411), new DeclinationTableEntry(-90, -50, 20.31442), new DeclinationTableEntry(-90, -40, 10.31474),
			new DeclinationTableEntry(-90, -30, 0.31506), new DeclinationTableEntry(-90, -20, -9.68463), new DeclinationTableEntry(-90, -10, -19.68434), new DeclinationTableEntry(-90, 0, -29.68407), new DeclinationTableEntry(-90, 10, -39.68383),
			new DeclinationTableEntry(-90, 20, -49.68364), new DeclinationTableEntry(-90, 30, -59.6835), new DeclinationTableEntry(-90, 40, -69.6834), new DeclinationTableEntry(-90, 50, -79.68337), new DeclinationTableEntry(-90, 60, -89.68339),
			new DeclinationTableEntry(-90, 70, -99.68346), new DeclinationTableEntry(-90, 80, -109.68359), new DeclinationTableEntry(-90, 90, -119.68376), new DeclinationTableEntry(-90, 100, -129.68398), new DeclinationTableEntry(-90, 110, -139.68424),
			new DeclinationTableEntry(-90, 120, -149.68453), new DeclinationTableEntry(-90, 130, -159.68484), new DeclinationTableEntry(-90, 140, -169.68516), new DeclinationTableEntry(-90, 150, -179.68548), new DeclinationTableEntry(-90, 160, 170.31421),
			new DeclinationTableEntry(-90, 170, 160.31392), new DeclinationTableEntry(-90, 180, 150.31365) };

	public CompassDeclination() {
		/**
		 * Nothing to see here. Deviation table is already set up. If debugging
		 * just dump the deviation table to stdout so we can see it was properly
		 * set up
		 */
	}

	public double FindDeclination(double lat, double lon) {
		/**
		 * If we get sent garbage lat/long then just return -99 to show error.
		 */
		if (Math.abs(lat) > 90 || Math.abs(lon) > 180) {
			return -99.0;
		}
		/**
		 * Scan down the table and look for the entry containing the value we
		 * want. We know they all increment by 10 so it makes the search easy.
		 */
		for (int i = 1; i < declinationTable.length; i++) {
			if (((int) (lat / 10) * 10 == (int) declinationTable[i].latitude) && ((int) (lon / 10) * 10 == (int) declinationTable[i].longitude)) {
				return declinationTable[i].declination;
			}
		}
		// If we ever get here something is sadly wrong
		return -99.0;
	}
}