<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="styles/docsStyles.css">
    <title>Documentation</title>
</head>
<body>
<header>
    <div id="sidebar" class="sidebar">
        <section>
            <h6>Methods</h6>
            <ul>
                <li>
                    <a href="#api">
                        /api
                        <span class="endpoint get"></span>
                    </a>
                </li>
                <li>
                    <a href="#add">
                        /add
                        <span class="endpoint get"></span>
                    </a>
                </li>
                <li>
                    <a href="#all">
                        /all
                        <span class="endpoint get"></span>
                    </a>
                </li>
                <li>
                    <a href="#delete">
                        /delete
                        <span class="endpoint get"></span>
                    </a>
                </li>
                <li>
                    <a href="#edit">
                        /edit
                        <span class="endpoint get"></span>
                    </a>
                </li>
                <li>
                    <a href="#register">
                        /register
                        <span class="endpoint get"></span>
                    </a>
                </li>
                <li>
                    <a href="#enable">
                        /enable
                        <span class="endpoint get"></span>
                    </a>
                </li>
                <li>
                    <a href="#disable">
                        /disable
                        <span class="endpoint get"></span>
                    </a>
                </li>
                <li>
                    <a href="#token">
                        /token
                        <span class="endpoint get"></span>
                    </a>
                </li>
            </ul>
        </section>
    </div>

</header>
<div class="main">
    <section class="doc-content" id="index-page">
        <section class="left-docs">
            <h3>
                <a>
                    Documentation
                </a>
            </h3>
        </section>
    </section>
    <section class="doc-content" id="index-page">
        <section class="left-docs">
            <h3>
                <a id="commonerrors">
                    Errors
                </a>
            </h3>
            <table>
                <thead>
                <tr>
                    <th>Code</th>
                    <th>Description</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1</td>
                    <td>Id not specified.</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Id is already in use.</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>User wasn't added.</td>
                </tr>
                <tr>
                    <td>4</td>
                    <td>User doesn't exist.</td>
                </tr>
                <tr>
                    <td>5</td>
                    <td>No users found.</td>
                </tr>
                <tr>
                    <td>6</td>
                    <td>User wasn't deleted.</td>
                </tr>
                <tr>
                    <td>7</td>
                    <td>Token doesn't have access!</td>
                </tr>
                <tr>
                    <td>8</td>
                    <td>Incorrect username or password.</td>
                </tr>
                </tbody>
            </table>
        </section>


        <section class="right-code">
            <p style="color: white">Example</p>
            <div title="Error form" class="language-json highlighter-rouge"><div class="highlight"><pre class="highlight"><code><span class="p">[</span><span class="w">
  </span><span class="p">{</span><span class="w">
    </span><span class="nl">"errorMessage"</span><span class="p">:</span><span class="w"> </span><span class="mi"><span class="s2">"Id not specified."</span><span class="p">,</span><span class="w">
    </span><span class="nl">"errorCode"</span><span class="p">:</span><span class="w"> </span><span class="s2">"1"</span>
    <span class="w">
  </span><span class="p">}</span><span class="w">
</span><span class="p">]</span><span class="w">
</span></code></pre></div></div>
        </section>
    </section>
    <section class="doc-content" id="index-page">
        <section class="left-docs">
            <h3>
                <a id="api">
                    /api
                    <span class="endpoint get"></span>
                </a>
            </h3>
            <p class="description">Get info by id</p>
            <h6>Parameters</h6>
            <dl>
                <dt>id</dt>
                <dd>Just enter id.</dd>
            </dl>
            <dl>
                <dt>Token</dt>
                <dd>Just enter token.</dd>
            </dl>
            <p>Returns infomation about user with id.</p>
        </section>


        <section class="right-code">
            <p style="color: white">Example</p>
            <div title="User information" class="language-json highlighter-rouge"><div class="highlight"><pre class="highlight"><code><span class="p">[</span><span class="w">
  </span><span class="p">{</span><span class="w">
    </span><span class="nl">"id"</span><span class="p">:</span><span class="w"> </span><span class="s2">"1"</span><span class="p">,</span><span class="w">
    </span><span class="nl">"firstname"</span><span class="p">:</span><span class="w"> </span><span class="s2">"user first name"</span><span class="p">,</span><span class="w">
    </span><span class="nl">"lastname"</span><span class="p">:</span><span class="w"> </span><span class="s2">"user last name"</span><span class="p">,</span><span class="w">
    </span><span class="nl">"country"</span><span class="p">:</span><span class="w"> </span><span class="s2">"user country"</span><span class="p">,</span><span class="w">
    </span><span class="nl">"email"</span><span class="p">:</span><span class="w"> </span><span class="s2">"user email"</span><span class="w"><span class="p">,</span>
    </span><span class="nl">"address"</span><span class="p">:</span><span class="w"> </span><span class="p">{</span><span class="w">
      </span><span class="nl">"city"</span><span class="p">:</span><span class="w"> </span><span class="s2">"user city"</span><span class="p">,</span><span class="w">
      </span><span class="nl">"street"</span><span class="p">:</span><span class="w"> </span><span class="s2">"user street"</span><span class="p">,</span><span class="w">
      </span><span class="nl">"houseNum"</span><span class="p">:</span><span class="w"> </span><span class="s2">"user house number"</span><span class="w">
    </span><span class="p">}</span><span class="w">
  </span><span class="p">}</span><span class="w">
</span><span class="p">]</span><span class="w">
</span></code></pre></div></div>
        </section>
        <section class="left-docs">
            <h3>
                <a id="add">
                    /add
                    <span class="endpoint get"></span>
                </a>
            </h3>
            <p class="description">Add user to DB</p>
            <h6>Parameters</h6>
            <dl>
                <dt>id</dt>
                <dd>Just enter id.</dd>
            </dl>
            <dl>
                <dt>Token</dt>
                <dd>Just enter token.</dd>
            </dl>
            <dl>
                <dt>firstname</dt>
                <dd>Just enter first name.</dd>
            </dl>
            <dl>
                <dt>lastname</dt>
                <dd>Just enter last name.</dd>
            </dl>
            <dl>
                <dt>country</dt>
                <dd>Just enter country.</dd>
            </dl>
            <dl>
                <dt>city</dt>
                <dd>Just enter city.</dd>
            </dl>
            <dl>
                <dt>street</dt>
                <dd>Just enter street.</dd>
            </dl>
            <dl>
                <dt>houseNum</dt>
                <dd>Just enter house number.</dd>
            </dl>
            <dl>
                <dt>email</dt>
                <dd>Just enter email.</dd>
            </dl>
            <p>Adds user to DB</p>
        </section>

        <section class="left-docs">
            <h3>
                <a id="all">
                    /all
                    <span class="endpoint get"></span>
                </a>
            </h3>
            <p class="description">Get all users</p>
            <h6>Parameters</h6>
            <dl>
                <dt>id</dt>
                <dd>Just enter id.</dd>
            </dl>
            <dl>
                <dt>Token</dt>
                <dd>Just enter token.</dd>
            </dl>
            <p>Returns infomation about all users.</p>
        </section>
        <section class="right-code">
            <p style="color: white">Example</p>
            <div title="All users" class="language-json highlighter-rouge"><div class="highlight"><pre class="highlight"><code><span class="p">[</span><span class="w">
  </span><span class="p">{</span><span class="w">
    </span><span class="nl">"id"</span><span class="p">:</span><span class="w"> </span><span class="s2">"user id"</span><span class="p">,</span><span class="w">
    </span><span class="nl">"lastname"</span><span class="p">:</span><span class="w"> </span><span class="s2">"user last name"</span><span class="p">,</span><span class="w">
    </span><span class="nl">"country"</span><span class="p">:</span><span class="w"> </span><span class="s2">"user country"</span><span class="p">,</span><span class="w">
    </span><span class="nl">"email"</span><span class="p">:</span><span class="w"> </span><span class="s2">"user email"</span><span class="p">,</span><span class="w">
    </span><span class="nl">"address"</span><span class="p">:</span><span class="w"> </span><span class="p">{</span><span class="w">
      </span><span class="n1">"city"</span><span class="p">:</span><span class="w"> </span><span class="s2">"user city"</span><span class="p">,</span><span class="w">
      </span><span class="nl">"street"</span><span class="p">:</span><span class="w"> </span><span class="s2">"user street"</span><span class="p">,</span><span class="w">
      </span><span class="nl">"houseNum"</span><span class="p">:</span><span class="w"> </span><span class="s2">"user house number"</span><span class="w">
    </span><span class="p">}</span><span class="w">
  </span><span class="p">},</span><span class="w">
  </span><span class="p">{</span><span class="w">
    </span><span class="nl">"id"</span><span class="p">:</span><span class="w"> </span><span class="s2">"user id"</span><span class="p">,</span><span class="w">
    </span><span class="nl">"lastname"</span><span class="p">:</span><span class="w"> </span><span class="s2">"user last name"</span><span class="p">,</span><span class="w">
    </span><span class="nl">"country"</span><span class="p">:</span><span class="w"> </span><span class="s2">"user country"</span><span class="p">,</span><span class="w">
    </span><span class="nl">"email"</span><span class="p">:</span><span class="w"> </span><span class="s2">"user email"</span><span class="p">,</span><span class="w">
    </span><span class="nl">"address"</span><span class="p">:</span><span class="w"> </span><span class="p">{</span><span class="w">
      </span><span class="n1">"city"</span><span class="p">:</span><span class="w"> </span><span class="s2">"user city"</span><span class="p">,</span><span class="w">
      </span><span class="nl">"street"</span><span class="p">:</span><span class="w"> </span><span class="s2">"user street"</span><span class="p">,</span><span class="w">
      </span><span class="nl">"houseNum"</span><span class="p">:</span><span class="w"> </span><span class="s2">"user house number"</span><span class="w">
    </span><span class="p">}</span><span class="w">
  </span><span class="p">}<span class="w">
</span><span class="p">]</span><span class="w">
</span></code></pre></div></div>
        </section>
        <section class="left-docs">
            <h3>
                <a id="delete">
                    /delete
                    <span class="endpoint get"></span>
                </a>
            </h3>
            <p class="description">Delete user from DB</p>
            <h6>Parameters</h6>
            <dl>
                <dt>id</dt>
                <dd>Just enter id.</dd>
            </dl>
            <dl>
                <dt>Token</dt>
                <dd>Just enter token.</dd>
            </dl>
            <dl>
                <p>Removes user from DB</p>
        </section>
        <section class="left-docs">
            <h3>
                <a id="edit">
                    /edit
                    <span class="endpoint get"></span>
                </a>
            </h3>
            <p class="description">Edit user in DB</p>
            <h6>Parameters</h6>
            <dl>
                <dt>Id</dt>
                <dd>Just enter id.</dd>
            </dl>
            <dl>
                <dt>Token</dt>
                <dd>Just enter token.</dd>
            </dl>
            <dl>
                <dt>firstname</dt>
                <dd>Just enter first name.</dd>
            </dl>
            <dl>
                <dt>lastname</dt>
                <dd>Just enter last name.</dd>
            </dl>
            <dl>
                <dt>country</dt>
                <dd>Just enter country.</dd>
            </dl>
            <dl>
                <dt>city</dt>
                <dd>Just enter city.</dd>
            </dl>
            <dl>
                <dt>street</dt>
                <dd>Just enter street.</dd>
            </dl>
            <dl>
                <dt>houseNum</dt>
                <dd>Just enter house number.</dd>
            </dl>
            <dl>
                <dt>email</dt>
                <dd>Just enter email.</dd>
            </dl>
            <dl>
                <p>Just list the parameters</p>
                <p>Edits user in DB</p>
        </section>
        <section class="left-docs">
            <h3>
                <a id="register">
                    /register
                    <span class="endpoint get"></span>
                </a>
            </h3>
            <p class="description">Registers new user</p>
            <h6>Parameters</h6>
            <dl>
                <dt>Username</dt>
                <dd>Just enter username.</dd>
            </dl>
            <dl>
                <dt>Password</dt>
                <dd>Just enter password.</dd>
            </dl>
            <dl>
                <p>Registers user to DB</p>
        </section>
        <section class="left-docs">
            <h3>
                <a id="enable">
                    /enable
                    <span class="endpoint get"></span>
                </a>
            </h3>
            <p class="description">"Activates" token</p>
            <h6>Parameters</h6>
            <dl>
                <dt>Token</dt>
                <dd>Just enter token.</dd>
            </dl>
                <p>"Activates" token</p>
        </section>
        <section class="left-docs">
            <h3>
                <a id="disable">
                    /disable
                    <span class="endpoint get"></span>
                </a>
            </h3>
            <p class="description">"Deactivates" token</p>
            <h6>Parameters</h6>
            <dl>
                <dt>Token</dt>
                <dd>Just enter token.</dd>
            </dl>
            <p>"Deactivates" token</p>
        </section>
        <section class="left-docs">
            <h3>
                <a id="token">
                    /token
                    <span class="endpoint get"></span>
                </a>
            </h3>
            <p class="description">Shows token and activation status of token</p>
            <h6>Parameters</h6>
            <dl>
                <dt>Username</dt>
                <dd>Just enter username.</dd>
            </dl>
            <dl>
                <dt>Password</dt>
                <dd>Just enter password.</dd>
            </dl>
            <p>Shows token and activation status of token</p>
        </section>
    </section>
</div>
</body>
</html>

