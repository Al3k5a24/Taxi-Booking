const plateData={
	"Toyota_Camry": ["CA789-TC", "NY456-TC", "TX123-TC", "FL321-TC", "IL654-TC"],
	"Toyota_Prius": ["WA111-TP", "CA222-TP", "OR333-TP", "NV444-TP", "CO555-TP"],
	"Honda_Accord": ["TX999-HA", "CA888-HA", "NY777-HA", "FL666-HA", "GA555-HA"],
	"Chevrolet_Malibu": ["MI101-CM", "OH202-CM", "PA303-CM", "IN404-CM", "WI505-CM"],
	"Ford_Fusion": ["CA123-FF", "TX234-FF", "FL345-FF", "NY456-FF", "NV567-FF"]
};

const carSelect=document.getElementById("carSelect");
const plateSelect=document.getElementById("plateNumber");

carSelect.addEventListener("change",function(){
	
	const selectedCar=this.value;
	const plates = plateData[selectedCar]||[];
	
	plateSelect.innerHTML = '<option value="" disabled selected>Select plate number</option>';
	
	plates.forEach(function (plate){
		const option=document.createElement("option");
		option.value=plate;
		option.textContent=plate;
		plateSelect.appendChild(option);
	});
});