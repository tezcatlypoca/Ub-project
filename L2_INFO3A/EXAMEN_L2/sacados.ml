module A=Array;;
(*
let poids=[|  2; 1; 3; 2 |];;
let utilite= [|  12; 10; 20; 15 |];;
*)

let poids=[|  2; 1; 2; 3 |];;
let utilite= [| 12; 11; 15; 8 |];;
let u_p_i = A.init 6 (function p-> A.make 4 (0));;
for p=1 to 5 do
	u_p_i.(p).(0) <- if poids.(0) <= p then utilite.(0) else 0;
	for i=1 to 3 do 
	   u_p_i.(p).(i) <- if poids.(i) > p then u_p_i.(p).(i-1)
		            else max u_p_i.(p).(i-1)
	 	 	             (utilite.(i) + u_p_i.(p-poids.(i)).(i-1))
done done;;

u_p_i ;;

