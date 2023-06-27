/*
		Flight Indicator
		Author : Sébastien Matton (seb_matton@hotmail.com)
		https://github.com/sebmatton/jQuery-Flight-Indicators
		More recent fork:
		https://github.com/Beta-Technologies/jQuery-Flight-Indicators
*/

var trackedPlane = null;

var options = {
	roll:0, 
	pitch:0, 
	heading:0, 
	vario:0, 
	turn:0,
	size:120, 
	showBox: false,
	img_directory : 'flightindicators/img/'	
}

var indicators = {
	attitude : null,
	heading : null,
	variometer : null,
	airspeed : null,
	altimeter : null,
	turnCoordinator : null
}


var airplanes = [];

// http://localhost:8080/pilot/setspeed?speed=500&uuid=7908dd58-b9bf-4e81-963e-d366dcd5877e
// http://localhost:8080/pilot/setheading?heading=15&uuid=aa4689aa-d5da-43e0-afbc-e885a8799f63
// http://localhost:8080/pilot/setpid?p=0.015&i=0.00008&d=0.00009&uuid=0dbbca06-78a7-4ab4-a94e-dbbd193ab543
// http://localhost:8080/pilot/setpid?p=0.015&i=0.00001&d=0.05&uuid=0dbbca06-78a7-4ab4-a94e-dbbd193ab543
// http://localhost:8080/pilot/setpid?p=0.02&i=0.00001&d=0.005&uuid=3a56f96c-7c02-4e28-85f6-e8c7d42c8bd4

function setSelectedPlane( plane ){
	for( var xy=0; xy<airplanes.length; xy++  ) {
		var planTemp = airplanes[xy];
		if( planTemp.name == plane  ){
			viewer.trackedEntity = planTemp;
			break;
		}
	}
}

function unselectPlane(){
	trackedPlane = null;
	viewer.trackedEntity = null;
}

function updateHud( payload ) {
	indicators.variometer.setVario( ( payload.rudderPosition / 10 ) * 4 );
	indicators.heading.setHeading( payload.currentAzimuth );
	indicators.airspeed.setAirSpeed( payload.speedKM * 10 );
	indicators.altimeter.setAltitude( payload.altitude );
    indicators.attitude.setRoll( payload.roll * 5 );
    indicators.attitude.setPitch( payload.pitch );
	indicators.turnCoordinator.setTurn( payload.roll * 5 );
	
	
	var longitudeString = payload.longitude.toString();
	var latitudeString = payload.latitude.toString();
	var coordHDMS = convertDMS(latitudeString,longitudeString);
	$("#mapLat").text( payload.latitude.toFixed(5) );
	$("#mapLon").text( payload.longitude.toFixed(5) );
	var utmVal = fromLatLon( parseFloat(latitudeString), parseFloat(longitudeString));
	var easting = utmVal.easting + "";
	var northing = utmVal.northing + "";
	var eaArr = easting.split(".");
	var noArr = northing.split(".");
	var eaDec = eaArr[1].substring(0,2);
	var noDec = noArr[1].substring(0,2);
	$("#mapHdmsLat").text( coordHDMS.lat + " " + coordHDMS.latCard );
	$("#mapHdmsLon").text( coordHDMS.lon + " " + coordHDMS.lonCard );
	$("#mapGeohash").text( "" );
	$("#mapUtm").text( "" );    
	$("#mapAlt").text( payload.altitude );
	$("#mapHead").text( payload.currentAzimuth );
}

function planeUp(){
	$.get( "/pilot/up", { meters: 500, uuid: trackedPlane } );
}
function planeDown(){
	$.get( "/pilot/down", { meters: 500, uuid: trackedPlane } );
}
function planeLeft(){
	$.get( "/pilot/turnleft", { degree: 10, uuid: trackedPlane } );
}
function planeRight(){
	$.get( "/pilot/turnright", { degree: 10, uuid: trackedPlane } );
}


function updatePlanes( payload ){
	
	var thePosition = Cesium.Cartesian3.fromDegrees( payload.longitude, payload.latitude, payload.altitude );					
	var heading = Cesium.Math.toRadians( payload.currentAzimuth - 90 );
	var pitch = Cesium.Math.toRadians( payload.pitch );
	var roll = Cesium.Math.toRadians( payload.roll );
	var hpr = new Cesium.HeadingPitchRoll(heading, pitch, roll);
	var theOrientation = Cesium.Transforms.headingPitchRollQuaternion(thePosition, hpr);	
	
	var found = false;
	for( var xy=0; xy<airplanes.length; xy++  ) {
		var planTemp = airplanes[xy];
		if( planTemp.name == payload.uuid   ){
			planTemp.position = thePosition;
			planTemp.orientation =  theOrientation;
			found = true;
			break;
		}
	}
	
	//Create a completely random color
	const color = Cesium.Color.fromRandom();


	if( !found ){
		var airPlane = new Cesium.Entity({
			name : payload.uuid,
			position: thePosition,
			orientation: theOrientation,
			show: true,
			model: {
				uri: 'common/models/air.glb',
				minimumPixelSize : 128,
				maximumScale : 500
			}/*,
			label: {
				text: payload.uuid,
				style: Cesium.LabelStyle.FILL,
				fillColor: Cesium.Color.BLACK,
				outlineWidth: 1,
				font: '10px Consolas',
				eyeOffset: new Cesium.Cartesian3(0.0, 170.0, 0.0)
			}*/
		});
		airplanes.push( airPlane );
		viewer.entities.add( airPlane );
	}	

	if( trackedPlane != null ) updateHud(payload);
		
}

