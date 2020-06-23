# back_end

## Getting Started:
1. Heroku deployment can be found here: <ins>https://secret-recipe-5.herokuapp.com/</ins></i></b>
2. *IMPORTANT* You need to include a clientid and clientsecret within axios calls to access API

```
 headers: {

   // btoa is converting our client id/client secret into base64
   Authorization: `Basic ${btoa('lambda-client:lambda-secret')}`,
   'Content-Type': 'application/x-www-form-urlencoded'

  }
```

## Endpoints:
* Example endpoint would be **https://secret-recipe-5.herokuapp.com/users** if using deployed backend.

#### Register/Login/Logout Endpoints

| Action | Endpoint | Description
|---|---|---|
| POST | `/users/createnewuser` | Creates a new user |
| POST | `/login` | Allows user to login and returns token |
| GET | `/logout` | Destroys current session and logs a user out |

#### Shape of User Required to Register (JSON):
```
{
    "username": "admin",
    "password": "password",
    "primaryemail": "email@email.com"
}
```
#### The Data Returned by Server After Registering:
```

```

#### Shape of User Required to Login (JSON):
```

```
#### The Data Returned by Server After Logging In:
```

```

#### User Endpoints

| Action | Endpoint | Description
|---|---|---|
| GET | `/users/users` | If user is logged in, returns an array of all other users |
| GET | `/users/user/:id` | If user is logged in, returns the user with the specified ID |
| GET | `/users/user/name/:userName` | Retrieves list of user with matching username|
| GET | `/users/user/name/:userLike` | Retrieves list of user(s) with like usernames|
| PUT | `/users/user/:id` | Edits user with specified ID |
|PATCH| `/users/user/:id` | Allows you to edit specific data points without sending entire required JSON(unlike PUT) |
| DELETE | `/users/user/:id` | Deletes user with specified ID |

##### NOTE: To access any of these user endpoint, you will need token authentication, which can be passed after a user login is performed.
  * Example:
  ```
axios.post('http://localhost:2019/login', `grant_type=password&username=${this.state.username}&password=${this.state.password}`, {

      headers: {

        // btoa is converting our client id/client secret into base64
        Authorization: `Basic ${btoa('lambda-client:lambda-secret')}`,
        'Content-Type': 'application/x-www-form-urlencoded'

      }

    })
      .then(res => {

        localStorage.setItem('token', res.data.access_token);
        this.props.history.push('/users');

      })
      .catch(err => console.dir(err));

    e.preventDefault();

  }
  ```

#### Recipe Endpoints

| Action | Endpoint | Description
|---|---|---|
| GET | `/recipes/recipe` | If user is logged in, returns an array of all recipes (theirs and those of other users) |
| GET | `/recipes/recipe/:id` | If user is logged in, returns the recipe with the specified ID |
| POST | `/recipes/recipe/:id` | Adds a recipe to currently logged in user |
| PUT | `/recipes/recipe/:id` | Edits recipe with specified ID |
| DELETE | `/recipes/recipe/:id` | Deletes recipe with specified ID |
