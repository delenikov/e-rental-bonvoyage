import React, {useState} from 'react';
import {Link} from 'react-router-dom';
import {SocialIcon} from 'react-social-icons';

const Footer = (props) => {

    const [location, setLocation] = useState(1);
    function change() {
        setLocation(document.getElementById('mySelect').value);
    }


    return (
        <footer className="text-black-50 mt-xl-5">
            <div className="container">
                <div className={"row"}>
                    <div>
                        <h5 className={"text-center"}>Изберете локација:</h5>
                    </div>
                    <div className={"d-flex justify-content-center"}>
                        <select onChange={()=>change()} id={"mySelect"}>
                            {props.locations.map((term) => {
                                    return (
                                        <option key={term.id} value={term.id}>{term.name}</option>
                                    );
                                }
                            )}
                        </select>
                        <Link className={"btn btn-warning"}
                              onClick={() => props.loadVehicles(location)}
                              to={`/locations/${location}/vehicles`}>Get Vehicles</Link>
                    </div>
                </div>

                <br/>
                <hr/>
                <br/>
                <div className="row">
                    <div className="col-md-4">
                        <h2>Партнерство</h2>
                        <p>
                            Нашата фирма е единствена во Македонија од ваков тип.
                            Следствено на тоа, ние имаме голем
                            број нa сериозни бизнис партнери и корисници.
                        </p>
                        <img className="w-25"
                             src="https://is2-ssl.mzstatic.com/image/thumb/Purple69/v4/2d/63/c3/2d63c3e6-9469-8a1a-9f3f-43f6e10e8dde/source/512x512bb.jpg"/>
                        <img className="w-25"
                             src="https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/BMW.svg/1200px-BMW.svg.png"/>
                        <img className="w-25" src="https://www.pss.com.mk/images/logo-dark.png"/>
                    </div>
                    <div className="col-md-4">
                        <h2>За нас</h2>
                        <p>Ние BonVoyage, како компанија и бренд повеќе се трудиме секогаш и навремено да ви
                            помогнеме со изнајмување на секаков вид возило.</p>
                        <h2>Бидете во тек со нас:</h2>
                        <SocialIcon url="https://facebook.com/in/#"/>
                        <SocialIcon url="https://twitter.com/in/#"/>
                        <SocialIcon url="https://instagram.com/in/#"/>
                        <SocialIcon url="https://youtube.com/in/#"/>
                        <SocialIcon url="https://linkedin.com/in/#"/>

                    </div>
                    <div className="col-md-4">
                        <h2>Локација</h2>
                        <iframe
                            src="https://www.google.com/maps/d/u/0/embed?mid=16rzikPakVhnf0vr9mGfOzxI0U8t12kz3&ehbc=2E312F"
                            width="360" height="300" allowFullScreen="" loading="lazy"></iframe>
                    </div>
                </div>

                <hr/>
                <p>All rights reserved © 2022 - BonVoyage</p>
            </div>
        </footer>
    )

}

export default Footer;