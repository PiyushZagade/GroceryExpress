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


  const searchinput = () => {
    let query = $("#search").val().trim();
    console.log(query);
    if (query === "") {
        $(".searchmsg").hide();
        $(".Availsearchmsg").hide();
    } else {
        let url = `https://groceryexpress-ynxu.onrender.com/search/${query}`;
        // let url = `http://localhost:8181/search/${query}`;
        fetch(url)
            .then((response) => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then((data) => {
                if (data.length === 0) {
                    
                    $(".searchmsg").html("<p>No results found</p>").show();
                    $(".Availsearchmsg").hide(); // Hide other div if not needed
                } else {
                    let resultHTML = `<div>`;
                    data.forEach((product) => {
                        resultHTML += `<a href='/productd/${product.id}'><p>${product.name}</p></a>`;
                    });
                    resultHTML += `</div>`;
                    
                    $(".searchmsg").html(resultHTML).show();
                    $(".Availsearchmsg").html(resultHTML).show();
                }
            })
            .catch((error) => {  
                console.error('Error fetching data:', error);
                $(".searchmsg").html("<p>Error fetching results. Please try again.</p>");
                $(".Availsearchmsg").html("<p>Error fetching results. Please try again.</p>");
                $(".searchmsg").show();
                $(".Availsearchmsg").show();
            });
    }
}

$(document).ready(() => {
    $(".searchmsg").hide();
    $(".Availsearchmsg").hide();
});

  function namecheck(){
    let nameip= document.getElementById("name").value;
    let nameerr= document.getElementById("nameerr");
 
    if(nameip.length==0){
       nameerr.innerHTML='<i class="fa-solid fa-circle-info"><span>Name is Required</span></i>';
       nameerr.style.cssText="color:rgb(208, 43, 43); font-size:15px";
       return false;
    }

    if(!nameip.match(/^[A-Za-z]{2,}\s[A-Za-z]{2,}$/)){
       nameerr.innerHTML='<i class="fa-solid fa-circle-info"><span>Atleast 2 characters</span></i>';
       nameerr.style.cssText="color:rgb(208, 43, 43); font-size:15px";
       return false;
   }

   nameerr.innerHTML='<i class="fa-solid fa-circle-check" style="color:green; margin-top:5px; font-size:19px;"></i>';
     return true;
  }

  function emailcheck(){
    let emailip= document.getElementById("email").value;
    let emailerr= document.getElementById("emailerr");
    if(emailip.length==0){
        emailerr.innerHTML='<i class="fa-solid fa-circle-info"><span>Email is Required</span></i>';
        emailerr.style.cssText="color:rgb(208, 43, 43); font-size:15px";
        return false;
     }
     if(!emailip.match(/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/)){
        emailerr.innerHTML='<i class="fa-solid fa-circle-info"><span>Invalid Email</span></i>';
        emailerr.style.cssText="color:rgb(208, 43, 43); font-size:15px";
        return false;
    }
    emailerr.innerHTML='<i class="fa-solid fa-circle-check" style="color:green; margin-top:5px; font-size:19px;"></i>';
     return true;

  }

  function phonecheck(){
    let phoneip= document.getElementById("phoneno").value;
    let phoneerr= document.getElementById("phoneerr");

    if(phoneip.length==0){
        phoneerr.innerHTML='<i class="fa-solid fa-circle-info"><span>Enter Phone No.</span></i>';
        phoneerr.style.cssText="color:rgb(208, 43, 43); font-size:15px";
        return false;
     }
     if(phoneip.match(/[A-Za-z]$/)){
        phoneerr.innerHTML='<i class="fa-solid fa-circle-info"><span>Enter number</span></i>';
        phoneerr.style.cssText="color:rgb(208, 43, 43); font-size:15px";
        return false;
     }
     
     if(phoneip.length<10){
        phoneerr.innerHTML='<i class="fa-solid fa-circle-info"><span>10 digits</span></i>';
        phoneerr.style.cssText="color:rgb(208, 43, 43); font-size:15px";
        return false;
     }
     if(phoneip.length>12){
        phoneerr.innerHTML='<i class="fa-solid fa-circle-info"><span>Invalid</span></i>';
        phoneerr.style.cssText="color:rgb(208, 43, 43); font-size:15px";
        return false;
     }
     phoneerr.innerHTML='<i class="fa-solid fa-circle-check" style="color:green; margin-top:5px; font-size:19px;"></i>';
     return true;
  }

  function passcheck(){
    let passip= document.getElementById("password").value;
    let passerr= document.getElementById("passerr");
    if(passip.length==0){
        passerr.innerHTML='<i class="fa-solid fa-circle-info"><span>Create password</span></i>';
        passerr.style.cssText="color:rgb(208, 43, 43); font-size:15px";
        return false;
     }
     if(passip.length<8){
        passerr.innerHTML='<i class="fa-solid fa-circle-info"><span>Atleast 8 char</span></i>';
        passerr.style.cssText="color:rgb(208, 43, 43); font-size:15px";
        return false;
     }
     passerr.innerHTML='<i class="fa-solid fa-circle-check" style="color:green; margin-top:5px; font-size:19px;"></i>';
     return true;
  }

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase();
}
  function citycheck(){
    let cityip= document.getElementById("city").value;
    let cityerr= document.getElementById("cityerr");
    if(cityip.length==0){
        cityerr.innerHTML='<i class="fa-solid fa-circle-info"><span>Enter City</span></i>';
        cityerr.style.cssText="color:rgb(208, 43, 43); font-size:15px";
        return false;
     }
     if(!cityip.match(/[A-Za-z]$/)){
        cityerr.innerHTML='<i class="fa-solid fa-circle-info"><span>Special Char/Num are not allowed</span></i>';
        cityerr.style.cssText="color:rgb(208, 43, 43); font-size:15px";
        return false;
     }
     if(cityip.length<3){
        cityerr.innerHTML='<i class="fa-solid fa-circle-info"><span>Invalid City</span></i>';
        cityerr.style.cssText="color:rgb(208, 43, 43); font-size:15px";
        return false;
     }
     cityerr.innerHTML='<i class="fa-solid fa-circle-check" style="color:green; margin-top:5px; font-size:19px;"></i>';
     return true;
  }
