var viewer;
var west = -88.67;
var south = -21.81;
var east = -27.56;
var north = 5.36;	
var homeLocation = Cesium.Rectangle.fromDegrees(west, south, east, north);
var mainEventHandler = null;

$( document ).ready(function() {
	initIndicators();
	initMap();
	mainEventHandler = new Cesium.ScreenSpaceEventHandler( scene.canvas );
	bindInterfaceElements();
	addMouseHoverListener();
	connectWs();
});

function initIndicators(){
	indicators.attitude = $.flightIndicator('#attitude', 'attitude', options );
	indicators.heading = $.flightIndicator('#heading', 'heading', options );
	indicators.variometer = $.flightIndicator('#variometer', 'variometer', options );
	indicators.airspeed = $.flightIndicator('#airspeed', 'airspeed', options );
	indicators.altimeter = $.flightIndicator('#altimeter', 'altimeter', options );
	indicators.turnCoordinator = $.flightIndicator('#turn_coordinator', 'turn_coordinator', options );	
}

function connectWs() {
	const ws2 = new SockJS( '/ws' );
	var stompClient = Stomp.over(ws2);
    stompClient.debug = () => {};
    stompClient.connect({}, (frame) => {
    	
      	stompClient.subscribe('/ping', (message) => {
      		console.log("PING RECEIVED!");
      	});

      	
      	stompClient.subscribe('/main_channel', (message) => {
      		if(message.body) {
      			var payload = JSON.parse(message.body);
      			// flightmamager.js
      			updatePlanes( payload ); 
      		}
      	});      	
      	
      	/*
		setInterval( () => {
			var data = { "test": Date.now() }
			stompClient.send("/ping", {priority: 0}, JSON.stringify(data) );
		}, 30000 );       	
      	*/
      	
	});
    
    
    
}


function initMap(){

		var baseOsmProvider = new Cesium.OpenStreetMapImageryProvider({
		    url : 'https://a.tile.openstreetmap.org/'
		});
			
		viewer = new Cesium.Viewer('cesiumContainer',{
			sceneMode : Cesium.SceneMode.SCENE2D,
			timeline: false,
			animation: false,
			baseLayerPicker: false,
			skyAtmosphere: false,
			fullscreenButton : false,
			geocoder : false,
			homeButton : false,
			infoBox : false,
			skyBox : false,
			sceneModePicker : false,
			selectionIndicator : false,
			navigationHelpButton : false,
		    imageryProvider: baseOsmProvider,
		    requestRenderMode : false,
		    shouldAnimate : true
		});
		
		camera = viewer.camera;
		scene = viewer.scene;

		scene.highDynamicRange = false; // HDR
		scene.globe.enableLighting = false;
		scene.globe.baseColor = Cesium.Color.WHITE;
		scene.screenSpaceCameraController.enableLook = false;
		scene.screenSpaceCameraController.enableCollisionDetection = true;
		scene.screenSpaceCameraController.inertiaZoom = 0;
		scene.screenSpaceCameraController.inertiaTranslate = 0;
		scene.screenSpaceCameraController.inertiaSpin = 0;
		scene.globe.depthTestAgainstTerrain = false;
		scene.pickTranslucentDepth = false;
		scene.useDepthPicking = false;
		scene.globe.tileCacheSize = 50;

		var imageryLayers = scene.imageryLayers;
		var helper = new Cesium.EventHelper();
		var totalTilesToLoad = 0;
		helper.add( viewer.scene.globe.tileLoadProgressEvent, function (event) {
			if( event > totalTilesToLoad ) totalTilesToLoad = event;
			if (event == 0) {
				$('.layerCounter').hide();
				$("#lyrCount").text( "" );
				$("#activeLayerContainer").height('90vh');
				var totalHeight= $("#activeLayerContainer").height() - 110 + 'px' ;
				$('#layerContainer').height( totalHeight );
				$("#loadingMap").remove();
			} else {
				var percent = 0;
				if ( totalTilesToLoad > 0 )	percent =  100 - Math.round( (event * 100) / totalTilesToLoad) ;
				$("#lyrCount").text( percent + " %" );
				$('.layerCounter').show();
			}
		});	

		
		imageryLayers.layerShownOrHidden.addEventListener(function (event) {
			//
		});
		imageryLayers.layerAdded.addEventListener(function (event) {
			//
		});
		imageryLayers.layerRemoved.addEventListener(function (event) {
			//
		});	
		
		var center = Cesium.Rectangle.center(homeLocation);
		var initialPosition = Cesium.Cartesian3.fromRadians(center.longitude, center.latitude, 6812323);
		var initialOrientation = new Cesium.HeadingPitchRoll.fromDegrees(0, -90, 0);
		scene.camera.setView({
		    destination: initialPosition,
		    orientation: initialOrientation,
		    endTransform: Cesium.Matrix4.IDENTITY
		});			
		
		
		/*
		var promise = Cesium.GeoJsonDataSource.load( "/calisto/sentinel/polygonized.geojson", {
			clampToGround: false,
	        stroke: Cesium.Color.fromBytes(255, 0, 0, 254),
	        fill: Cesium.Color.RED,
	        strokeWidth: 2,	
	   	});
		promise.then(function(dataSource) {
			var entities = dataSource.entities.values;
			for (var i = 0; i < entities.length; i++) {
				var entity = entities[i];
				var entityPosition = entity.polygon.hierarchy._value.positions[0];
				putMarker( entityPosition, "fire.png" )
				entity.name = 'FIRE';
			    viewer.entities.add( entity );
			    scene.requestRender();
			}	
		});
		*/
			
};

function bindInterfaceElements() {
    $(".cesium-viewer-bottom").hide();
    $(".cesium-viewer-navigationContainer").hide();
    $(".navigation-controls").hide();
};



function addMouseHoverListener() {

	mainEventHandler.setInputAction( function(movement) {
		var position = getMapPosition3D2D( movement.endPosition );
		$('#cesiumContainer').css('cursor','default');
		try {
			if ( position ) updatePanelFooter( position );
			const pickedObject = this.viewer.scene.pick( movement.endPosition );
		    if ( Cesium.defined( pickedObject ) ) {
				const entity = pickedObject.id;
				if( entity.name == 'IMAGE' )  $('#cesiumContainer').css('cursor','pointer');
			}
		} catch ( err ) {
			console.log( err );
		}
	}, Cesium.ScreenSpaceEventType.MOUSE_MOVE );
	
	mainEventHandler.setInputAction( function( movement ) {
		try {
		    const pickedObject = this.viewer.scene.pick( movement.position );
		    var pickedObjects = scene.drillPick(new Cesium.Cartesian2(movement.position.x, movement.position.y)); 
		    if ( pickedObjects.length > 0 ) {
		    	// showImageInfo( pickedObjects );
			} else {
				// $("#sentinelResultPanel").hide();
			}			
		} catch ( err ) {
			// ignore
		}
	}, Cesium.ScreenSpaceEventType.LEFT_CLICK );
	
	
};

function updatePanelFooter( position ) {
	cartographic = Cesium.Ellipsoid.WGS84.cartesianToCartographic( position );
	var longitudeString = Cesium.Math.toDegrees(cartographic.longitude).toFixed(10);
	var latitudeString = Cesium.Math.toDegrees(cartographic.latitude).toFixed(10);    	    
	mapPointerLatitude = latitudeString.slice(-15);
	mapPointerLongitude = longitudeString.slice(-15);
	var coordHDMS = convertDMS(mapPointerLatitude,mapPointerLongitude);
	$("#mapLat").text( mapPointerLatitude );
	$("#mapLon").text( mapPointerLongitude );
	var utmVal = fromLatLon( parseFloat(mapPointerLatitude), parseFloat(mapPointerLongitude));
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
}

function getMapPosition3D2D( movement ){

	if ( viewer.scene.mode == Cesium.SceneMode.SCENE2D ) {
        var position = viewer.camera.pickEllipsoid(movement, scene.globe.ellipsoid);
        if (position) {
        	return position;
        } 
	}
	
	if ( viewer.scene.mode == Cesium.SceneMode.SCENE3D ) {
		var ray = viewer.camera.getPickRay(movement);
		var position = viewer.scene.globe.pick(ray, viewer.scene);
		if (Cesium.defined(position)) {
			return position;
		} 
	}
	
}

function convertDMS(lat, lng) {
    var latitude = toDegreesMinutesAndSeconds(lat);
    var latitudeCardinal = lat >= 0 ? "N" : "S";
    var longitude = toDegreesMinutesAndSeconds(lng);
    var longitudeCardinal = lng >= 0 ? "E" : "W";
    var ret = {};
    ret.lat = latitude;
    ret.lon = longitude;
    ret.latCard = latitudeCardinal;
    ret.lonCard = longitudeCardinal;
    return ret;
}

function toDegreesMinutesAndSeconds(coordinate) {
    var absolute = Math.abs(coordinate);
    var degrees = Math.floor(absolute);
    var minutesNotTruncated = (absolute - degrees) * 60;
    var minutes = Math.floor(minutesNotTruncated);
    var seconds = Math.floor((minutesNotTruncated - minutes) * 60);
    if( minutes < 10 ) minutes = "0" + minutes;
    if( seconds < 10 ) seconds = "0" + seconds;
    return degrees + "\xB0 " + minutes + "\' " + seconds + "\"";
}

