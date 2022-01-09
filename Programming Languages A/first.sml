(*comment*)

val x = 34; (* int *)
(* static environment: x : int *)
(* dynamic environment: x -> 34 *)

val y = 17;
(* static environment: x : int, y : int *)
(* dynamic environment: x -> 34, y -> 17 *)

val z = x + y;
(* static environment: x : int, y : int, z : int *)
(* dynamic environment: x -> 34, y -> 17, z -> 51 *)

val abs_of_z = if z < 0 then 0 - z else z;
val abs_of_z_simpler = abs z;

fun pow(x : int, y : int) =
    if y = 0
    then 1
    else x * pow(x, y-1)

val pair = (1, true)

fun list_product(xs : int list) =
    if null(xs)
    then 1
    else hd(xs) * list_product(tl(xs))

(* recursive functions *)
(* countup/countdown *)
(* append *)
(* max *)
	   
