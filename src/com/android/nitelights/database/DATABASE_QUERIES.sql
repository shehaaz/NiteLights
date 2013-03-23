Example Venue JSON

{
    "venues": [
        {
            "nid": "68",
            "title": "Korovas",
            "address": "123 fake street",
            "rating": "100",
            "geolocation": "POINT (-73.57812 45.516377)"
        }
    ],
    "success": 1
}

GET ALL VENUES from TABLE = NODE

SELECT * 
FROM  `node` 
WHERE  `type` =  'venue'

LNG and LAT from the site

SELECT * 
FROM    `field_data_field_geo` 

POINT (-73.57812 45.516377)

Venue Address

SELECT * 
FROM  `field_data_field_address` 

Venue Rating
SELECT * 
FROM  `field_data_field_rating`

------------------------------------------------------------------------------------------------------
COMMITS: TABLE = REGISTRATION (LOOK AT entity_id and user_uid)

SELECT * 
FROM  `registration` 
LIMIT 0 , 100

USERS: TABLE =  users

SELECT * 
FROM  `users` 
LIMIT 0 , 100

WIRE DATA FROM USER 58:

SELECT * 
FROM  `heartbeat_activity` 
WHERE  `uid` =58

