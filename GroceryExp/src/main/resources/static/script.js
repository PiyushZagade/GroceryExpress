// contact popup 

let contactpopup=document.getElementById("contact");

function showpopup(){
    contactpopup.classList.add("Popupcontactdisplay");
  
}
function closepopup(){
    contactpopup.classList.remove("Popupcontactdisplay");
    
}



let confirmpopup =document.getElementById("Confirmed");

function thankyou(){
    confirmpopup.classList.add("Thankyoupurchase");

}

function closeyou(){
    confirmpopup.classList.remove("Thankyoupurchase");
   
}



function toggleMenu() {
    const navLinks = document.querySelector('.nav-links');
    navLinks.classList.toggle('active');
  }
  

