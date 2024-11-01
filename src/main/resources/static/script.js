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
        // let url = `https://groceryexpress-ynxu.onrender.com/search/${query}`;
        let url = `http://localhost:8181/search/${query}`;
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

  

