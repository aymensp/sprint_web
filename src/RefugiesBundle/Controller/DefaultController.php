<?php

namespace RefugiesBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('RefugiesBundle:Default:index.html.twig');
    }
}
