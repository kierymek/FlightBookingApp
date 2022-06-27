import React, { useState } from "react";

function Order(props) {
    const [traveler, setTraveler] = useState("");
    const [fname, setFname] = useState("Harryette");
    const [lname, setLname] = useState("Mullen");
    const [dob, setDob] = useState("1990-01-01");
    const [order, setOrder] = useState("");


    function makeTraveler(event){
        event.preventDefault();
        fetch("api/traveler/", {
            method: "post",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                data: {
                    fname: fname,
                    lname: lname,
                    dob: dob
                }
            })
        })
        .then((response) => response.json())
        .then((json) => {
            setTraveler(json);
        });
    }

    console.log("Order -> makeTraveler: ", traveler)

    function submit(event, props){
        event.preventDefault();
        fetch("api/order/", {
            method: "post",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                data: {
                    type: "flight-order",
                    flightOffers: props.confirmation.flightOffers,
                    travelers: [traveler]
                }
            })
        })
        .then((response) => response.json())
        .then((json) => {
            setOrder(json);
            props.setOrder(json);
        });
    }

    console.log("Order -> submit order flight offer: ", props.confirmation.flightOffers, order);


    return (
        <div>
            <form onSubmit={(e) => makeTraveler(e)}>
                <label htmlFor="dob">Date of Birth:</label>
                <input type="date"
                    onChange={(e) => setDob(e.target.value)}
                    id="dob"
                    name="dob"
                    required /><br></br>
                <label>First Name: </label>
                <input value={fname} onChange={(e) => setFname(e.target.value)} required></input><br></br>
                <label>Last Name: </label>
                <input value={lname} onChange={(e) => setLname(e.target.value)} required></input><br></br>
                <input type="submit" value="Submit Traveler Info" />
            </form>
            { traveler &&
                <form onSubmit={(e) => submit(e, props)}>
                    <input type="submit" value="Book Flight" />
                </form>
            }
            { props.order &&
                <div>
                    <div>Flight Booked! Here are the details:</div>
                    <div>{JSON.stringify(props.order)}</div>
                </div>
            }
        </div>
    );
};

export default Order;