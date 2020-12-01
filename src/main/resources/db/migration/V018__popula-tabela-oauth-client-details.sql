insert into oauth_client_details (
  client_id, resource_ids, client_secret, 
  scope, authorized_grant_types, web_server_redirect_uri, authorities,
  access_token_validity, refresh_token_validity, autoapprove
)
values (
  'fretex-mobile', null, '$2a$10$Kuydfq7DTScQoTVDpZTnLeFO08pl3hivz8uxl2YD43/8VcvbswaYO',
  'READ,WRITE', 'password', null, null,
  60 * 60 * 6, 60 * 24 * 60 * 60, null
);