<?php
/**
 * Created by PhpStorm.
 * User: arafe
 * Date: 12/02/2019
 * Time: 20:44
 */

namespace UserBundle\Controller;



use UserBundle\Entity\User;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Request;

/**
 * @Route("inscription")
 */
class InscriptionController extends Controller
{
    /**
     *
     * @Route("/", name="inscription")
     */
    public function newAction(Request $request)
    {

        $user = new User();


        $formUser = $this->createForm(  'UserBundle\Form\UserType', $user);


        $formUser->handleRequest($request);


        if ($formUser->isSubmitted() && $formUser->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($user);
            $em->flush();

            return $this->redirectToRoute('homepage', array('id' => $user->getId()));
        }




        return $this->render('@User/Default/inscription.html.twig', array(
            'formUser' => $formUser->createView(),

        ));
    }

}