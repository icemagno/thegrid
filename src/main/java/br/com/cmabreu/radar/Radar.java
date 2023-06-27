package br.com.cmabreu.radar;

import br.com.cmabreu.pilot.Airplane;
import ch.hsr.geohash.GeoHash;

public class Radar {
	private Airplane airplane;
	
	public Radar( Airplane airplane ) {
		this.airplane = airplane;
	}
	
/*
	1	≤ 5,000km	×	5,000km
	2	≤ 1,250km	×	625km
	3	≤ 156km	×	156km
	4	≤ 39.1km	×	19.5km
	5	≤ 4.89km	×	4.89km
	6	≤ 1.22km	×	0.61km
	7	≤ 153m	×	153m
	8	≤ 38.2m	×	19.1m
	9	≤ 4.77m	×	4.77m
	10	≤ 1.19m	×	0.596m
	11	≤ 149mm	×	149mm
	12	≤ 37.2mm	×	18.6mm
*/	
	
	public String getSensorArea( ) {
		
		GeoHash gh = GeoHash.withCharacterPrecision( this.airplane.getLatitude(), this.airplane.getLongitude(), 7 );
		GeoHash[] adjacent = gh.getAdjacent();
		
		StringBuilder sb = new StringBuilder();
		sb.append( gh.toBase32() ).append("!");
		
		sb.append( adjacent[0].toBase32() ).append("#");
		sb.append( adjacent[0].getNorthernNeighbour().toBase32() ).append("#");
		sb.append( adjacent[1].toBase32() ).append("#");
		sb.append( adjacent[2].toBase32() ).append("#");
		sb.append( adjacent[2].getEasternNeighbour().toBase32() ).append("#");
		sb.append( adjacent[3].toBase32() ).append("#");
		sb.append( adjacent[4].toBase32() ).append("#");
		sb.append( adjacent[4].getSouthernNeighbour().toBase32() ).append("#");
		sb.append( adjacent[5].toBase32() ).append("#");
		sb.append( adjacent[6].toBase32() ).append("#");
		sb.append( adjacent[6].getWesternNeighbour().toBase32() ).append("#");
		sb.append( adjacent[7].toBase32() );
		
		/*
		JSONObject geoHashData = new JSONObject();
		String hash = gh.toBase32();
		geoHashData.put("center", hash);
		geoHashData.put("n",  adjacent[0].toBase32() );
		geoHashData.put("ne", adjacent[1].toBase32());
		geoHashData.put("e",  adjacent[2].toBase32());
		geoHashData.put("se", adjacent[3].toBase32());
		geoHashData.put("s",  adjacent[4].toBase32());
		geoHashData.put("sw", adjacent[5].toBase32());
		geoHashData.put("w",  adjacent[6].toBase32());
		geoHashData.put("nw", adjacent[7].toBase32());
		geoHashData.put("topNorth",    adjacentNorth.toBase32());
		geoHashData.put("rightEast",   adjacentEast.toBase32());
		geoHashData.put("bottomSouth", adjacentSouth.toBase32());
		geoHashData.put("leftWest",    adjacentWest.toBase32());
		*/

		
		return sb.toString();
	}

}
