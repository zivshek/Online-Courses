(* Dan Grossman, Coursera PL, HW2 Provided Code *)

(* if you use this function to compare two strings (returns true if the same
   string), then you avoid several of the functions in problem 1 having
   polymorphic types that may be confusing *)
fun same_string(s1 : string, s2 : string) =
    s1 = s2

(* put your solutions for problem 1 here *)
	     (* 1.a *)
fun all_except_option (s, ss) =
    case ss of
	[] => NONE
      | x::ss' => if same_string(s, x)
		  then SOME ss'
		  else
		      case all_except_option(s, ss') of
			  NONE => NONE
			| SOME ss' => SOME(x::ss')					   

(* 1.b *)
fun get_substitutions1 (slistlist, s) =
    case slistlist of
	[] => []
      | firstList::restList => case all_except_option(s, firstList) of
				   NONE => get_substitutions1(restList, s)
				 | SOME ss => ss @ get_substitutions1(restList, s)

(* 1.c *)
fun get_substitutions2 (slistlist, s) =
    let
	fun aux (sll, acc) =
	    case sll of
		[] => acc
	      | firstList::restList => case all_except_option(s, firstList) of
					   NONE => aux(restList, acc)
					 | SOME ss => aux(restList, acc @ ss)
    in
	aux(slistlist, [])
    end

(* 1.d *)
fun similar_names (sll, {first, last, middle}) =
    let
	fun aux (ss, acc) =
	    case ss of
		[] => acc
	      | x::xs' => aux(xs', acc @ [{first = x, last = last, middle = middle}])
    in
	aux(get_substitutions2(sll, first), [{first = first, last = last, middle = middle}])
    end
	
								      
(* you may assume that Num is always used with values 2, 3, ..., 10
   though it will not really come up *)
datatype suit = Clubs | Diamonds | Hearts | Spades
datatype rank = Jack | Queen | King | Ace | Num of int 
type card = suit * rank

datatype color = Red | Black
datatype move = Discard of card | Draw 

exception IllegalMove

(* put your solutions for problem 2 here *)
fun card_color (suit, rank) =
    case suit of
	Clubs => Black
      | Spades => Black
      | Diamonds => Red
      | Hearts => Red

fun card_value (suit, rank) =
    case rank of
	Num i => i
      | Ace => 11
      | _ => 10

fun remove_card (cards, card, e) =
    let
	fun aux (cards, (result, found)) =
	    case cards of
		[] => (result, found)
	      | c::rest => if c = card
			   then (result @ rest, true)
			   else aux(rest, (c::result, false))
    in
	case aux(cards, ([], false)) of
	    (_, false) => raise e
	 | (result, _) => result
    end
	
fun all_same_color cards =
    case cards of
	[] => true
      | _::[] => true
      | head::(neck::rest) => if card_color(head) = card_color(neck)
			      then all_same_color(neck::rest)
			      else false
				       
fun sum_cards cards =
    let
	fun aux (cards, sum) =
	    case cards of
		[] => sum
	      | head::rest => aux(rest, sum + card_value(head))
    in
	aux(cards, 0)
    end

fun score (cards, goal) =
    let
	fun preliminary_score cards  =
	    case sum_cards(cards) of
		sum => if sum > goal
		       then 3 * (sum - goal)
		       else goal - sum
    in
	if all_same_color cards
	then preliminary_score(cards) div 2
	else preliminary_score cards
    end

fun officiate (deck, moves, goal) =
    let
	fun play (deck, moves, hand) =
	    case moves of
		[] => score(hand, goal)
	      | move::moves' => case move of
				    Discard x => play(deck, moves', remove_card(hand, x, IllegalMove))
				  | Draw => case deck of
						[] => score(hand, goal)
					      | top::deck' => play(deck', moves', top::hand)
    in
	play(deck, moves, [])
    end
	
	
	
