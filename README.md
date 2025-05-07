Guide till att testa program!!!

Använd postman med (get, post, put och delete)


Användning av "Movies"

POST MAPPING:
https://localhost:8081/movies

Skriv i body: 
{
    "title": "name of movie", 
    "director": "director of movie"
}



DELETE MAPPING: 
https://localhost:8081/movies/{id}    tar bort film med angivet id



PUT MAPPING:
https://localhost:8081/movies/{id}     ändra infon i body på samma sätt som i post mapping för att ändra information om film med angivet id



GET MAPPING:
https://localhost:8081/movies     visar alla filmer

https://localhost:8081/movies/{id}     visar film efter id nummer

https://localhost:8081/movies/{id}/reviews      visar film efter id och alla recensioner som är skrivna på den filmen

https://localhost:8081/movies/{id}/statistics      visar film efter id och "statistik" kopplad till filmen

https://localhost:8081/movies/{id}/combined       visar film efter id, samt alla recensioner och statistik till filmen





Användning av "Reviews"

POST MAPPING:
https://localhost:8082/reviews

Skriv i body: 
{
       "movieId": movieid,
        "text": "your review",
        "userId": userid,
        "rating": your rating of movie    (ex 6.8)
}



DELETE MAPPING: 
https://localhost:8082/reviews/{id}    tar bort recension med angivet id



PUT MAPPING:
https://localhost:8082/reviews/{id}     ändra infon i body på samma sätt som i post mapping för att ändra information om recension med angivet id



GET MAPPING:
https://localhost:8082/reviews     visar alla recensioner

https://localhost:8082/reviews/{id}     visar rencesion efter id nummer

https://localhost:8082/reviews/{id}/user      visar recension efter id och vilken "userid" som användare som skapat recensionen har

https://localhost:8082/reviews/{id}/movie     visar recension efter angivet id och vilken film den är kopplad till

https://localhost:8082/reviews/{id}/combined       visar recension, film och vilket userid använaderen har som skrivet recensionen







Användning av "Users"

POST MAPPING:
https://localhost:8083/users

Skriv i body: 
{
        "username": "your name",
        "email": "your email"
}



DELETE MAPPING: 
https://localhost:8083/users/{id}    tar bort user med angivet id



PUT MAPPING:
https://localhost:8083/users/{id}     ändra infon i body på samma sätt som i post mapping för att ändra information om user med angivet id



GET MAPPING:
https://localhost:8083/users     visar alla users som finns

https://localhost:8083/users/{id}     visar user med angivet id

https://localhost:8083/users/{id}/reviews     visar alla recensioner som användaren med anvgivet id har skrivit








Användning av "Statistics"

POST MAPPING:
https://localhost:8084/statistics

Skriv i body: 
{
        "movieId": movieid,
        "rating": rating på film   (ex 8.4),
        "releaseYear": year of release,
        "totalGrossing": total grossing of the movie      (OBS!!! Detta är en int, inte större värde än 2 147 483 648)
}



DELETE MAPPING: 
https://localhost:8084/statistics/{id}    tar bort statistik med angivet id



PUT MAPPING:
https://localhost:8084/statistics     ändra infon i body på samma sätt som i post mapping för att ändra information om statistiken med angivet id



GET MAPPING:
https://localhost:8084/statistics     visar all statistik för alla filmen

https://localhost:8084/statistics/{id}     visar statistik med anvigvet id

https://localhost:8084/statistics/{id}/movie     visar statistik för filmen med "movieId" {id}

https://localhost:8084/statistics/{id}/combined       visar statistik samt vilken film den tillhör där id't på statistics är {id}
