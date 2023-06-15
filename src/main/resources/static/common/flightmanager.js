/*
		Flight Indicator
		Author : Sébastien Matton (seb_matton@hotmail.com)
		https://github.com/sebmatton/jQuery-Flight-Indicators
		More recent fork:
		https://github.com/Beta-Technologies/jQuery-Flight-Indicators
*/
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

var defaultAltitude = 10000;
var airplanes = [];

// http://localhost:8080/pilot/setspeed?speed=500&uuid=7908dd58-b9bf-4e81-963e-d366dcd5877e
// http://localhost:8080/pilot/setheading?heading=15&uuid=2698a65a-2ab6-4163-95d7-daa77e2618c1
// http://localhost:8080/pilot/setpid?p=0.0&i=0.009&d=5.0&uuid=ce5dee82-7263-4146-b483-aaa335f46abd

function updatePlanes( payload ){
	indicators.variometer.setVario( ( payload.rudderPosition / 10 ) * 4 );
	indicators.heading.setHeading( payload.currentAzimuth );
	indicators.airspeed.setAirSpeed( payload.speedKM * 10 );
	indicators.altimeter.setAltitude( defaultAltitude );
	
	var thePosition = Cesium.Cartesian3.fromDegrees( payload.longitude, payload.latitude, defaultAltitude );					
	var heading = Cesium.Math.toRadians( payload.currentAzimuth - 90 );
	
	var pitch = Cesium.Math.toRadians(0.0);
	var roll = Cesium.Math.toRadians(0.0);
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
	
	if( !found ){
		var airPlane = new Cesium.Entity({
			name : payload.uuid,
			position: thePosition,
			orientation: theOrientation,
			show: true,
			model: {
				uri: 'common/models/air.glb',
				minimumPixelSize : 128,
				maximumScale : 500,
				color: Cesium.Color.RED

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
	
}


// Update at 20Hz
var increment = 0;
/*
setInterval(function() {
    // Airspeed update
    airspeed.setAirSpeed(80+80*Math.sin(increment/10));

    // Attitude update
    attitude.setRoll(30*Math.sin(increment/10));
    attitude.setPitch(50*Math.sin(increment/20));

    // Altimeter update
    altimeter.setAltitude(10*increment);
    altimeter.setPressure(1000+3*Math.sin(increment/50));
    increment++;
    
    // TC update
    turn_coordinator.setTurn(30*Math.sin(increment/10));

    // Heading update
    heading.setHeading(increment);
    
    // Vario update
    variometer.setVario(2*Math.sin(increment/10));
    
}, 50);   
*/