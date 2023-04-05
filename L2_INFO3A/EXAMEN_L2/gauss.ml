(* algorithme de reduction des vecteurs d'un reseau *)
let arrondir a b = (* arrondit a:b *)
(a + (b/2))/b;;

let rec reduire  ((vx,vy),(ux,uy)) = (* le plus long, leplus court *)
let uv = ux * vx + uy * vy in
let uu = ux * ux + uy * uy in
let vv = vx*vx + vy*vy in
if vv < uu then reduire ((ux,uy),(vx,vy)) 
else
if 0=uu then ((vx,vy),(ux,uy)) 
else
let q= arrondir uv  uu in
let (rx,ry)= (vx - q * ux, vy - q * uy) in
let rr= rx*rx + ry*ry in
if rr >= uu then ((rx,ry),(ux,uy))
else ((ux,uy),(rx,ry)) ;;

reduire ((13, 11),(5, 7));;

let rec fixpt f x0 =
	let x1= f x0 in
	if x1=x0 then x1
	else fixpt f x1;;

let gauss ((vx,vy),(ux,uy)) =
	fixpt reduire ((vx,vy),(ux,uy));;
