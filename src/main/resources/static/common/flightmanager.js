/*
		Flight Indicator
		Author : Sébastien Matton (seb_matton@hotmail.com)
		https://github.com/sebmatton/jQuery-Flight-Indicators
		More recent fork:
		https://github.com/Beta-Technologies/jQuery-Flight-Indicators
*/


var options = {
	roll:50, 
	pitch:-20, 

	heading:150, 

	vario:-5, 

	turn:0,

	size:100, 
	showBox: false,
	img_directory : 'flightindicators/img/'	
}


var attitude = $.flightIndicator('#attitude', 'attitude', options );
var heading = $.flightIndicator('#heading', 'heading', options );
var variometer = $.flightIndicator('#variometer', 'variometer', options );
var airspeed = $.flightIndicator('#airspeed', 'airspeed', options );
var altimeter = $.flightIndicator('#altimeter', 'altimeter', options );
var turn_coordinator = $.flightIndicator('#turn_coordinator', 'turn_coordinator', options );

// Update at 20Hz
var increment = 0;
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